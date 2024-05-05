package com.example.greenifyme.ui.database_manager.account.accountModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.account.Account
import com.example.greenifyme.data.account.AccountDao
import com.example.greenifyme.data.account.populateAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountModel(private val accountRepository: AccountDao) : ViewModel() {

    private val _accountUiState = MutableStateFlow(AccountUiState(selectedAccount = null))
    val accountUiState: StateFlow<AccountUiState> = _accountUiState

    val accountItems: StateFlow<List<Account>> = accountRepository.getAccounts().map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )

    suspend fun saveItem() {
        viewModelScope.launch {
            if (validateInput()) {
                accountRepository.insert(accountUiState.value.accountFields.toAccount())
            }
        }
    }

    suspend fun updateItem() {
        viewModelScope.launch {
            if (validateInput()) {
                accountRepository.update(accountUiState.value.accountFields.toAccount())
            }
        }
    }

    fun deleteItem(account: Account) {
        viewModelScope.launch {
            accountRepository.delete(account)
        }
    }

    fun setBottomSheet(value: Boolean) {
        _accountUiState.update {
            it.copy(
                showBottomSheet = value,
            )
        }
    }

    fun openSheetAndSwitchToCreateMode() {
        _accountUiState.update {
            it.copy(
                accountFields = AccountFields(),
                openSheetForEditing = false,
                showBottomSheet = true,
                isEntryValid = true
            )
        }
    }

    fun deleteAll(alsoPopulate: Boolean = false) {
        viewModelScope.launch {
            accountRepository.deleteAll()

            if (alsoPopulate) {
                for (account in populateAccount()) {
                    accountRepository.insert(account)
                }
            }
        }
    }

    fun updateUiState(itemDetails: AccountFields) {
        _accountUiState.update {
            it.copy(
                accountFields = itemDetails,
                isEntryValid = validateInput(itemDetails)
            )
        }
    }

    fun onItemClick(account: Account) {
        _accountUiState.update {
            it.copy(
                selectedAccount = account,
                accountFields = account.toAccountFields(),
                isEntryValid = true,
                openSheetForEditing = true,
                showBottomSheet = true
            )
        }
    }

    private fun validateInput(
        uiState: AccountFields = accountUiState.value.accountFields
    ): Boolean {
        return with(uiState) {
            name.isNotBlank() && email.isNotBlank() && password.isNotBlank()
        }
    }

    fun onSearch(force: Boolean = false) {
        if (_accountUiState.value.searchQuery.isNotEmpty() && !force) {

            _accountUiState.update {
                it.copy(
                    searchQuery = "",
                )
            }
        } else {
            _accountUiState.update {
                it.copy(
                    isSearching = false,
                    searchQuery = "",
                )
            }
        }
    }

    fun onQueryChange(query: String) {
        _accountUiState.update {
            it.copy(
                searchQuery = query,
                isSearching = if (query.isNotEmpty()) true else it.isSearching
            )
        }
    }

    fun getSearchItems(): List<Account> {
        val searchQuery = _accountUiState.value.searchQuery
        return if (searchQuery.isEmpty()) listOf()
        else accountItems.value.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }
}

