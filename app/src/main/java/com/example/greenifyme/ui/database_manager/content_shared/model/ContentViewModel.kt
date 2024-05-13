package com.example.greenifyme.ui.database_manager.content_shared.model

import com.example.greenifyme.data.Account
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.data.DataObjectType
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Record
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface ContentViewModel {
    val databaseItems: StateFlow<List<DataObject>>
    val uiState: MutableStateFlow<ContentUiState>
    val scope: CoroutineScope
    val repository: GreenRepository
    val destination: DBManagerNavDestination

    fun saveItem() {
        if (validateInput()) {
            repository.insert(
                when (val item = uiState.value.itemState) {
                    is AccountState -> item.toAccount()
                    is RecordState -> item.toRecord()
                    is MaterialState -> item.toMaterial()
                }, scope
            )
        }
    }

    fun updateItem() {
        if (validateInput()) {
            repository.update(
                when (val item = uiState.value.itemState) {
                    is AccountState -> item.toAccount()
                    is RecordState -> item.toRecord()
                    is MaterialState -> item.toMaterial()
                }, scope
            )
        }

    }

    fun deleteItem(item: DataObject) = repository.delete(item, scope)


    fun setBottomSheet(value: Boolean) {
        uiState.update {
            it.copy(showBottomSheet = value)
        }
    }

    fun openSheetAndSwitchToCreateMode() {
        uiState.update {
            it.copy(
                itemState =
                when (destination) {
                    DBManagerNavDestination.Account -> AccountState()
                    DBManagerNavDestination.Record -> RecordState()
                    DBManagerNavDestination.Material -> MaterialState()
                },
                openSheetForEditing = false,
                showBottomSheet = true,
                isEntryValid = true
            )
        }
    }


    fun validateInput(changedUiState: ItemState = uiState.value.itemState): Boolean {
        return when (changedUiState) {
            is AccountState -> with(changedUiState) {
                name.isNotBlank() && email.isNotBlank() && password.isNotBlank()
            }

            is RecordState -> true
            is MaterialState -> true
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
        repository.deleteAll(
            when (uiState.value.itemState) {
                is AccountState -> DataObjectType.ACCOUNT
                is RecordState -> DataObjectType.RECORD
                is MaterialState -> DataObjectType.MATERIAL
            }, scope, alsoPopulate
        )
    }


    fun onListItemClick(item: DataObject) {
        uiState.update {
            it.copy(
                selectedAccount = item,
                itemState = when (item) {
                    is Account -> item.toAccountState()
                    is Record -> item.toRecordState()
                    is Material -> item.toMaterialState()
                },
                isEntryValid = true,
                openSheetForEditing = true,
                showBottomSheet = true
            )
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
                    is Record -> it.recordId == searchQuery.toInt() || it.accountId == searchQuery.toInt()
                    is Material -> it.materialId == searchQuery.toInt()
                }
            }
        else
            databaseItems.value.filter {
                when (it) {
                    is Account -> it.name.contains(searchQuery, ignoreCase = true)
                    is Record -> false
                    is Material -> it.name.contains(
                        searchQuery,
                        ignoreCase = true
                    ) || it.category.description.contains(searchQuery, ignoreCase = true)
                }
            }
    }
}



