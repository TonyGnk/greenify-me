package com.example.greenifyme.ui.database_manager.content_shared.model

import com.example.greenifyme.data.Account
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.data.Record
import com.example.greenifyme.data.account.AccountDao
import com.example.greenifyme.data.record.RecordDao
import com.example.greenifyme.data.record.populateRecord
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface ContentViewModel {
    val databaseItems: StateFlow<List<DataObject>>
    val uiState: MutableStateFlow<ContentUiState>
    val scope: CoroutineScope
    val accountRepository: AccountDao
    val recordRepository: RecordDao
    val destination: DBManagerNavDestination


    fun saveItem() {
        scope.launch {
            if (validateInput()) {
                when (uiState.value.itemState) {
                    is AccountState -> accountRepository.insert(
                        (uiState.value.itemState as AccountState).toAccount()
                    )

                    is RecordState -> recordRepository.insert(
                        (uiState.value.itemState as RecordState).toRecord()
                    )
                }
            }
        }
    }

    fun updateItem() {
        scope.launch {
            if (validateInput()) {
                when (val fields = uiState.value.itemState) {
                    is AccountState -> accountRepository.update(
                        fields.toAccount()
                    )

                    is RecordState -> recordRepository.update(
                        fields.toRecord()
                    )
                }
            }
        }
    }

    fun deleteItem(item: DataObject) {
        scope.launch {
            when (item) {
                is Account -> accountRepository.delete(item)
                is Record -> recordRepository.delete(item)
            }
        }
    }

    fun setBottomSheet(value: Boolean) {
        uiState.update {
            it.copy(
                showBottomSheet = value,
            )
        }
    }

    fun openSheetAndSwitchToCreateMode() {
        uiState.update {
            it.copy(
                itemState =
                when (destination) {
                    DBManagerNavDestination.Account -> AccountState()
                    DBManagerNavDestination.Record -> RecordState()
                },
                openSheetForEditing = false,
                showBottomSheet = true,
                isEntryValid = true
            )
        }
    }


    fun validateInput(
        changedUiState: ItemState = uiState.value.itemState
    ): Boolean {
        return when (changedUiState) {
            is AccountState -> with(changedUiState) {
                name.isNotBlank() && email.isNotBlank() && password.isNotBlank()
            }

            is RecordState -> true
        }

    }

    fun searchOnSearch(closeAlso: Boolean = false) {
        if (uiState.value.searchQuery.isNotEmpty() && !closeAlso)
            uiState.update { it.copy(searchQuery = "") }
        else
            uiState.update { it.copy(isSearching = false, searchQuery = "") }
    }

    fun searchOnQueryChange(query: String) {
        uiState.update {
            it.copy(
                searchQuery = query,
                isSearching = if (query.isNotEmpty()) true else it.isSearching
            )
        }
    }


    fun updateFields(uiFields: ItemState) {
        uiState.update {
            it.copy(
                itemState = uiFields,
                isEntryValid = validateInput(uiFields)
            )
        }
    }


    fun deleteAll(alsoPopulate: Boolean = false) {
        scope.launch {
            when (destination) {
                DBManagerNavDestination.Account -> {
                    accountRepository.deleteAll()
                    if (alsoPopulate) for (account in populateAccount()) {
                        accountRepository.insert(account)
                    }
                }

                DBManagerNavDestination.Record -> {
                    recordRepository.deleteAll()
                    if (alsoPopulate) for (record in populateRecord()) {
                        recordRepository.insert(record)
                    }
                }
            }
        }
    }

    fun onListItemClick(item: Any) {
        when (item) {
            is Account -> uiState.update {
                it.copy(
                    selectedAccount = item,
                    itemState = item.toAccountFields(),
                    isEntryValid = true,
                    openSheetForEditing = true,
                    showBottomSheet = true
                )
            }

            is Record -> uiState.update {
                it.copy(
                    selectedAccount = item,
                    itemState = item.toAccountFields(),
                    isEntryValid = true,
                    openSheetForEditing = true,
                    showBottomSheet = true
                )
            }
        }

    }

    fun searchOnSearchButton() {
        uiState.update {
            it.copy(
                searchQuery = "",
                isSearching = false
            )
        }
    }

    fun searchGetResults(): List<DataObject> {
        val searchQuery = uiState.value.searchQuery
        return if (searchQuery.isEmpty()) listOf()
        else if (searchQuery.toIntOrNull() != null)
            databaseItems.value.filter {
                when (it) {
                    is Account -> it.id == searchQuery.toInt()
                    is Record -> it.id == searchQuery.toInt() || it.accountId == searchQuery.toInt()
                }
            }
        else
            databaseItems.value.filter {
                when (it) {
                    is Account -> it.name.contains(searchQuery, ignoreCase = true)
                    is Record -> false
                }
            }
    }
}



