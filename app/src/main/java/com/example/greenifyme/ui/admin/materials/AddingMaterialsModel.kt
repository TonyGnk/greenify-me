package com.example.greenifyme.ui.admin.materials

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.Material
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class AddingMaterialsModel(repository: GreenRepository) : ViewModel() {
    val totalMaterialsState: StateFlow<List<Material>> = repository.getMaterialsOrderByCategory()
        .map { it }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )

}