package com.example.greenifyme.ui.admin.materials

import android.telecom.Call.Details
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.data.RecyclingCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AdminEditMaterialsModel(val repository: GreenRepository) : ViewModel() {
    private val _state = MutableStateFlow(AddingMaterialState())
    val state: StateFlow<AddingMaterialState> = _state

    val totalMaterialsState: StateFlow<List<Material>> = repository.getMaterialsOrderByCategory()
        .map { it }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )

    fun onItemClick(material: Material) {
        _state.update {
            it.copy(
                selectedMaterial = material,
                dialogType = AddingMaterialDialogType.Details,
                selectedCategory = material.category,
                showDialog = true
            )
        }
    }

    fun onDeleteMaterial() {
        val selected = state.value.selectedMaterial
        _state.update {
            it.copy(
                showDialog = false,
                selectedMaterial = null
            )
        }
        selected?.let {
            repository.delete(it, viewModelScope)
        }
    }

    fun onGramFieldChange(gramField: String) {
        _state.update {
            it.copy(gramField = gramField)
        }
    }

    fun onPieceFieldChange(pieceField: String) {
        _state.update {
            it.copy(pieceField = pieceField)
        }
    }

    fun onNameFieldChange(nameField: String) {
        _state.update {
            it.copy(nameField = nameField)
        }
    }

    fun showCategoryPicker() {
        _state.update {
            it.copy(
                showCategoryPicker = true,
            )
        }
    }

    fun pickCategory(category: RecyclingCategory) {
        _state.update {
            it.copy(
                selectedCategory = category,
            )
        }
        addMaterial()
    }

    private fun addMaterial() {
        val gramField = state.value.gramField
        val pieceField = state.value.pieceField
        val selectedCategory = state.value.selectedCategory
        val nameField = state.value.nameField

        val grams = gramField.toIntOrNull()
        val pieces = pieceField.toIntOrNull()

        val material = Material(
            name = nameField,
            category = selectedCategory!!,
            type = if (grams != null && pieces != null) {
                Both(
                    pointsPerGram = grams,
                    pointsPerPiece = pieces
                )
            } else if (grams != null) {
                Grams(
                    pointsPerGram = grams
                )
            } else if (pieces != null) {
                Pieces(
                    pointsPerPiece = pieces
                )
            } else {
                throw IllegalArgumentException("Invalid type")
            }
        )
        repository.insert(material, viewModelScope)

        _state.update {
            it.copy(
                showDialog = false,
                gramField = "",
                pieceField = "",
                selectedCategory = null,
                nameField = "",
                showCategoryPicker = false
            )
        }
    }

    fun onFabClick() {
        _state.update {
            it.copy(
                showDialog = true,
                dialogType = AddingMaterialDialogType.New
            )
        }
    }

    fun onAddMaterialFirst() {
        _state.update {
            it.copy(
                showCategoryPicker = true,
            )
        }
    }

    fun onDialogDismiss() {
        _state.update {
            it.copy(
                showDialog = false,
                showCategoryPicker = false,
                selectedMaterial = null,
                nameField = "",
                gramField = "",
                pieceField = "",
            )
        }
    }
}

data class AddingMaterialState(
    val showCategoryPicker: Boolean = false,
    val showDialog: Boolean = false,
    val selectedMaterial: Material? = null,
    val dialogType: AddingMaterialDialogType = AddingMaterialDialogType.Details,
    val nameField: String = "",
    val gramField: String = "",
    val pieceField: String = "",
    val selectedCategory: RecyclingCategory? = null,
)

enum class AddingMaterialDialogType {
    Details, New
}