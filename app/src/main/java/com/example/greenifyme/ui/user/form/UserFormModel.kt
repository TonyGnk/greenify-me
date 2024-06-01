package com.example.greenifyme.ui.user.form

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.NotificationHandler
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.data.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.enums.EnumEntries

class UserFormModel(val repository: GreenRepository, val account: Account) : ViewModel() {


    var form = MutableStateFlow(Form(1, account.accountId))

    val state = MutableStateFlow(UserFormState())

    init {
        viewModelScope.launch {
            repository.getFormLatestIndex().collect { index ->
                form.update { it.copy(formId = index + 1) }
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
            notificationHandler.showNewFormNotification(text)

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
                dialogDestination = FormDialogDestination.QUANTITY
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

        val track = Track(formId = idOfTrack, materialId = material.materialId, quantity = 1)
        state.update {
            it.copy(
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