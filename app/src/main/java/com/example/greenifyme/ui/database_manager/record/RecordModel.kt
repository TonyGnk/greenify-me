package com.example.greenifyme.ui.database_manager.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RecordModel(
    override val destination: DBManagerNavDestination,
    override val repository: GreenRepository,
) : ViewModel(), ContentViewModel {
    override val uiState = MutableStateFlow(ContentUiState(destination, selectedAccount = null))
    override val databaseItems =
        repository.getRecords()
            .map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )
    override val scope = viewModelScope
}