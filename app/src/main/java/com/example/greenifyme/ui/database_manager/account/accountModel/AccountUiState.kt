package com.example.greenifyme.ui.database_manager.account.accountModel

import com.example.greenifyme.data.account.Account
import com.example.greenifyme.data.account.hashPassword


data class AccountUiState(
    val accountFields: AccountFields = AccountFields(),
    var selectedAccount: Account?,
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val searchItemList: List<Account> = listOf(),
    val openSheetForEditing: Boolean = false,
    val showBottomSheet: Boolean = false,
    val isEntryValid: Boolean = true,
)

data class AccountFields(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isAdmin: Boolean = false,
)

fun AccountFields.toAccount(): Account = Account(
    id = id,
    name = name,
    email = email,
    password = hashPassword(password),
    isAdmin = isAdmin
)

fun Account.toAccountFields(): AccountFields = AccountFields(
    id = id,
    name = name,
    email = email,
    password = password,
    isAdmin = isAdmin
)