package com.example.greenifyme.ui.database_manager.content_shared.model

import android.annotation.SuppressLint
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.data.account.hashPassword
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import java.text.SimpleDateFormat


data class ContentUiState(
    val type: DBManagerNavDestination,
    val strings: ContentStringsState = when (type) {
        DBManagerNavDestination.Account -> AccountStringsState()
        DBManagerNavDestination.Record -> RecordStringsState()
        DBManagerNavDestination.Material -> MaterialStringsState()
    },
    val itemState: ItemState = when (type) {
        DBManagerNavDestination.Account -> AccountState()
        DBManagerNavDestination.Record -> RecordState()
        DBManagerNavDestination.Material -> MaterialState()
    },
    var showFab: Boolean = when (type) {
        DBManagerNavDestination.Account -> true
        DBManagerNavDestination.Record -> false
        DBManagerNavDestination.Material -> false
    },
    var selectedAccount: DataObject?,
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val searchItemList: List<Account> = listOf(),
    val openSheetForEditing: Boolean = false,
    val showBottomSheet: Boolean = false,
    val isEntryValid: Boolean = true,
)


sealed class ItemState

data class AccountState(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isAdmin: Boolean = false,
) : ItemState()

data class RecordState(
    val id: Int = 0,
    val accountId: String = "",
    val materialId: Int = 0,
    val hasAdminViewed: Boolean = false,
    val createdAt: Long = 0
) : ItemState()


data class MaterialState(
    val id: Int = 0,
    val category: RecyclingCategory = RecyclingCategory.OTHER,
    val name: String = "",
    val options: String = "",
    val hasSubcategories: Boolean = true
) : ItemState()

fun MaterialState.toMaterial(): Material = Material(
    materialId = id,
    category = category,
    name = name,
    options = options,
    hasSubcategories = hasSubcategories
)

fun Material.toMaterialState(): MaterialState = MaterialState(
    id = materialId,
    category = category,
    name = name,
    options = options,
    hasSubcategories = hasSubcategories
)


fun AccountState.toAccount(): Account = Account(
    id = id,
    name = name,
    email = email,
    password = hashPassword(password),
    isAdmin = isAdmin
)

fun Account.toAccountState(): AccountState = AccountState(
    id = id,
    name = name,
    email = email,
    password = password,
    isAdmin = isAdmin
)

fun RecordState.toRecord(): Form = com.example.greenifyme.data.Form(
    formId = id,
    accountId = accountId.toInt(),
    materialId = materialId,
    hasAdminViewed = hasAdminViewed,
    createdAt = createdAt
)


fun Form.toRecordState(): RecordState = RecordState(
    id = formId,
    accountId = accountId.toString(),
    hasAdminViewed = hasAdminViewed,
    createdAt = createdAt
)

// No needed for now
@SuppressLint("SimpleDateFormat")
fun findEpochTimeFrom(givenDate: String): Long {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(givenDate)
    if (date != null) {
        return date.time
    }
    return 0
}

@SuppressLint("SimpleDateFormat")
fun findTimeFromEpoch(givenData: Long): String {
    val date = SimpleDateFormat("HH:mm dd/MM/yy").format(givenData)
    return date
}