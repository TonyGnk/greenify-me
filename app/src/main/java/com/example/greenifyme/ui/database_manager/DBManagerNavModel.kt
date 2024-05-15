package com.example.greenifyme.ui.database_manager

import androidx.lifecycle.ViewModel
import com.example.greenifyme.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DBManagerNavModel : ViewModel() {

    // Also these 2 lines are the most common thing in compose and are always used like this
    //Here we create the data class (= A struct of all the necessary variables for the UI)
    //We keep the private val _uiState for use here in viewmodel
    private val _uiState = MutableStateFlow(NavScreenState())

    // And if we update the _uiState, the changes are reflected in the public uiState
    val uiState = _uiState.asStateFlow()
    // This is the most efficient way identical to Apple's Framework, SwiftUI, Flutter, Kotlin Multiplatform

    //Typical we don't store other variables in the viewmodel

    //This function is called when the user clicks on a navigation item
    fun navigateTo(destination: DBManagerNavDestination) {
        _uiState.update { it.copy(destination = destination) }
        savedDestination = destination
    }
}


enum class DBManagerNavDestination { Account, Record, Material }

// This is an exception to the rule of not storing variables in the viewmodel/Ui State
// Select the navigation item Material, then press back. Select again Database Manager.
// You are back at Material this is because the destination is stored in the savedDestination
// When you leave the Manager, all the viewModel data is lost, but savedDestination is not
// because it is a static variable outside the viewModel
private var savedDestination: DBManagerNavDestination? = null

//This is a data class, a struct in other languages. A efficient way
// to store all the data needed for the UI
data class NavScreenState(
    //The first time the app starts, the destination is Account
    //?: Means: if savedDestination is null, use NavDestination.Account otherwise use savedDestination
    val destination: DBManagerNavDestination = savedDestination ?: DBManagerNavDestination.Account,

    val navigationList: List<DBManagerNavItem> = DBManagerNavDestination.entries.map {
        when (it) {
            DBManagerNavDestination.Account -> DBManagerNavItem(
                destination = DBManagerNavDestination.Account,
                titleRes = R.string.accountDatabase,
                filledVector = R.drawable.baseline_manage_accounts_24,
                outlineVector = R.drawable.outline_manage_accounts_24
            )

            DBManagerNavDestination.Record -> DBManagerNavItem(
                destination = DBManagerNavDestination.Record,
                titleRes = R.string.recordDatabaseName,
                filledVector = R.drawable.baseline_receipt_long_24,
                outlineVector = R.drawable.outline_receipt_long_24
            )

            DBManagerNavDestination.Material -> DBManagerNavItem(
                destination = DBManagerNavDestination.Material,
                titleRes = R.string.materialDatabase,
                filledVector = R.drawable.baseline_category_24,
                outlineVector = R.drawable.outline_category_24
            )
        }
    }
)



