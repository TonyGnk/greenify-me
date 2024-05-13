package com.example.greenifyme.ui.database_manager.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.account.AccountDao
import com.example.greenifyme.data.record.RecordDao
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AccountModel(
    override val destination: DBManagerNavDestination,
    override val accountRepository: AccountDao,
    override val recordRepository: RecordDao,
) : ViewModel(), ContentViewModel {
    override val uiState = MutableStateFlow(ContentUiState(destination, selectedAccount = null))
    override val databaseItems =
        accountRepository.getAll()
            .map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )
    override val scope = viewModelScope
}