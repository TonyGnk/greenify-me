package com.example.greenifyme.ui.user.form

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.NotificationHandler
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.data.Track
import com.example.greenifyme.ui.admin.notifications.NotificationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.enums.EnumEntries

class UserFormModel(
    val repository: GreenRepository,
    val account: Account,
    private val useSampleData: Boolean
) : ViewModel() {


    var form = MutableStateFlow(Form(1, accountId = account.accountId))

    val state = MutableStateFlow(UserFormState())

    init {
        viewModelScope.launch {
            repository.getFormLatestIndex().collect { index ->
                form.update {
                    it.copy(
                        formId = if (index == null) 1 else (index + 1)
                    )
                }
            }
        }
    }


    fun quitForm(activity: Activity) = activity.finish()


    fun submitForm(activity: Activity) {
        if (!state.value.askPermission) {
            state.update { it.copy(askPermission = true) }
        } else {
            val notificationHandler = NotificationHandler(activity)
            val text = activity.getString(R.string.user_submit_form, account.name)
            val formNotification = NotificationItem.FormNotification(
                createdAt = form.value.createdAt,
                formId = form.value.formId,
                hasViewed = form.value.hasAdminViewed,
                accountName = account.name,
                accountId = account.accountId
            )
            notificationHandler.showNewFormNotification(text, useSampleData, formNotification)

            repository.insert(form.value, viewModelScope)
            state.value.trackMaterialsMap.forEach {
                repository.insert(it.first, viewModelScope)
            }
            activity.finish()
        }
    }


    fun onCategorySelected(category: RecyclingCategory) {
        viewModelScope.launch {
            repository.getMaterialsWith(category).collect { items ->
                state.update {
                    it.copy(
                        materials = items,
                        selectedCategory = category,
                        dialogDestination = FormDialogDestination.MATERIAL
                    )
                }
            }
        }
    }

    fun selectMaterial(material: Material) {
        state.update {
            it.copy(
                selectedMaterial = material,
                dialogDestination = FormDialogDestination.QUANTITY,
                isGramsSelected = when (material.type) {
                    is Both -> QuantityType.BOTH_SHOW_GRAMS
                    is Grams -> QuantityType.ONLY_GRAMS
                    is Pieces -> QuantityType.ONLY_PIECES
                }
            )
        }
    }

    fun setDialog(value: Boolean) {
        state.update {
            it.copy(
                showDialog = value,
                dialogDestination = FormDialogDestination.CATEGORY
            )
        }
    }

    fun addTrack() {
        val idOfTrack = form.value.formId
        val material = state.value.selectedMaterial
        val selectedMaterial = state.value.selectedMaterial
        val points = when (state.value.isGramsSelected) {
            QuantityType.ONLY_GRAMS -> selectedMaterial.type.pointsPerGram
            QuantityType.ONLY_PIECES -> selectedMaterial.type.pointsPerPiece
            QuantityType.BOTH_SHOW_GRAMS -> selectedMaterial.type.pointsPerGram
            QuantityType.BOTH_SHOW_PIECES -> selectedMaterial.type.pointsPerPiece
        }

        val givenQuantityClean = state.value.query.replace(',', '.')
        val givenQuantity = givenQuantityClean.toFloatOrNull()

        val track = Track(
            formId = idOfTrack,
            materialId = material.materialId,
            //Multiply by points to get the total points
            quantity = givenQuantity?.times(points) ?: 0f
        )
        //if field is not empty
        if (givenQuantity != null) state.update {
            it.copy(
                query = "",
                trackToAdd = track,
                trackMaterialsMap = it.trackMaterialsMap + Pair(track, material)
            )
        }
        setDialog(false)
    }

    fun deleteTrack(mutableEntry: Pair<Track, Material>) {
        state.update {
            it.copy(
                trackMaterialsMap = it.trackMaterialsMap.toMutableList().apply {
                    remove(mutableEntry)
                }
            )
        }
        repository.delete(mutableEntry.first, viewModelScope)
    }

    fun onDialogQuantityChangeSelection(type: QuantityType) {
        state.update {
            it.copy(isGramsSelected = type)
        }
    }

    fun onDialogQuantityQueryChange(string: String) {
        state.update {
            it.copy(query = string)
        }
    }

    fun onDismissButton() {
        when (state.value.dialogDestination) {
            FormDialogDestination.CATEGORY -> state.update {
                it.copy(
                    showDialog = false,
                    dialogDestination = FormDialogDestination.CATEGORY
                )
            }

            FormDialogDestination.MATERIAL -> state.update {
                it.copy(dialogDestination = FormDialogDestination.CATEGORY)
            }

            FormDialogDestination.QUANTITY -> state.update {
                it.copy(dialogDestination = FormDialogDestination.MATERIAL)
            }
        }
    }
}

data class UserFormState(
    val materials: List<Material> = listOf(),
    val trackToAdd: Track? = null,
    val trackMaterialsMap: List<Pair<Track, Material>> = listOf(),
    val selectedMaterial: Material = Material(0, RecyclingCategory.OTHER, ""),
    val isGramsSelected: QuantityType = QuantityType.ONLY_GRAMS,
    val query: String = "",
    val recyclingCategories: EnumEntries<RecyclingCategory> = RecyclingCategory.entries,
    val selectedCategory: RecyclingCategory = RecyclingCategory.PLASTIC,
    val showDialog: Boolean = true,
    val dialogDestination: FormDialogDestination = FormDialogDestination.CATEGORY,
    val askPermission: Boolean = false,
    val strings: UserFormStrings = UserFormStrings()
)

data class UserFormStrings(
    val appBarLabel: Int = R.string.user_form_app_bar_label,
    val appBarCancel: Int = R.string.user_form_app_bar_cancel,
    val actionButtonsAdd: Int = R.string.user_form_action_buttons_add,
    val dialogCancel: Int = R.string.user_form_dialog_cancel,
    val dialogBack: Int = R.string.user_form_dialog_back,
    val dialogAdd: Int = R.string.user_form_dialog_add,
    val dialogSelect: Int = R.string.user_form_dialog_select
)

enum class FormDialogDestination(val title: Int) {
    CATEGORY(R.string.user_form_dialog_category_title),
    MATERIAL(R.string.user_form_dialog_material_title),
    QUANTITY(R.string.user_form_dialog_quantity_title)
}

enum class QuantityType {
    ONLY_GRAMS, ONLY_PIECES, BOTH_SHOW_GRAMS, BOTH_SHOW_PIECES
}