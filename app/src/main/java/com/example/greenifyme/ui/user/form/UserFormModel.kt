package com.example.greenifyme.ui.user.form

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.data.DataObjectType
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.data.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.enums.EnumEntries

class UserFormModel(val repository: GreenRepository) : ViewModel() {

    private val currentAccountId = 1

    var form = MutableStateFlow(Form(1, currentAccountId))
    val tracksOfForm: MutableStateFlow<List<Track>> = MutableStateFlow(listOf())
    val state = MutableStateFlow(UserFormState())

    init {
        viewModelScope.launch {
            val latestIndex = repository.getFormLatestIndex()
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

            latestIndex.collect { index ->
                val newForm = Form(index + 1, currentAccountId)
                form.value = newForm
                repository.insert(newForm, viewModelScope)

                repository.getTracks(newForm.formId)
                    .collect { tracks ->
                        tracksOfForm.value = tracks
                    }
            }
        }
    }

//    var form = MutableStateFlow(Form(1, currentAccountId))
//
//    init {
//        viewModelScope.launch {
//            repository.getFormLatestIndex().collect { index ->
//                form.update {
//                    Form(index + 1, currentAccountId)
//                }
//                repository.insert(form.value, viewModelScope)
//                repository.getTracks(index).collect { tracks ->
//                    tracksOfForm.update {
//                        tracks
//                    }
//                }
//            }
//        }
//    }
//
//    val tracksOfForm: MutableStateFlow<List<Track>> = MutableStateFlow(listOf())
//    val state = MutableStateFlow(UserFormState())


    fun quitForm(activity: Activity) {
        repository.delete(form.value, viewModelScope)
        activity.finish()
    }

    fun submitForm(activity: Activity) {
        activity.finish()
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
        val materialId = state.value.selectedMaterial.materialId

        val track = Track(formId = idOfTrack, materialId = materialId, quantity = 1)
        repository.insert(track, viewModelScope)
        viewModelScope.launch {
            repository.getTracks(idOfTrack).collect { tracks ->
                tracksOfForm.update {
                    tracks
                }
            }
        }
        setDialog(false)
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
    val selectedMaterial: Material = Material(0, RecyclingCategory.OTHER, ""),
    val recyclingCategories: EnumEntries<RecyclingCategory> = RecyclingCategory.entries,
    val selectedCategory: RecyclingCategory = RecyclingCategory.PLASTIC,
    val showDialog: Boolean = true,
    val dialogDestination: FormDialogDestination = FormDialogDestination.CATEGORY,
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