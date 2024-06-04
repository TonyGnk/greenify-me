package com.example.greenifyme.ui.database.manager.navigation

import androidx.lifecycle.ViewModel
import com.example.greenifyme.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ManagerNavigationModel : ViewModel() {

    private val _state = MutableStateFlow(NavigationScreenState())
    val state = _state.asStateFlow()

    fun navigateTo(destination: DatabaseManagerNavigationDestination) {
        _state.update { it.copy(destination = destination) }
        savedDestination = destination
    }
}

enum class DatabaseManagerNavigationDestination { Account, Form, Track, Material }

private var savedDestination: DatabaseManagerNavigationDestination? = null

data class NavigationScreenState(
    val destination: DatabaseManagerNavigationDestination = savedDestination
        ?: DatabaseManagerNavigationDestination.Account,
    val navigationList: List<ManagerNavigationItem> = DatabaseManagerNavigationDestination.entries.map {
        when (it) {
            DatabaseManagerNavigationDestination.Account -> ManagerNavigationItem(
                destination = DatabaseManagerNavigationDestination.Account,
                titleRes = R.string.manager_navigation_account_title,
                filledVector = R.drawable.user_pen_solid,
                outlineVector = R.drawable.user_pen
            )

            DatabaseManagerNavigationDestination.Form -> ManagerNavigationItem(
                destination = DatabaseManagerNavigationDestination.Form,
                titleRes = R.string.manager_navigation_form_title,
                filledVector = R.drawable.to_do_solid,
                outlineVector = R.drawable.to_do
            )

            DatabaseManagerNavigationDestination.Track -> ManagerNavigationItem(
                destination = DatabaseManagerNavigationDestination.Track,
                titleRes = R.string.manager_navigation_track_title,
                filledVector = R.drawable.pen_field_solid,
                outlineVector = R.drawable.pen_field
            )

            DatabaseManagerNavigationDestination.Material -> ManagerNavigationItem(
                destination = DatabaseManagerNavigationDestination.Material,
                titleRes = R.string.manager_navigation_material_title,
                filledVector = R.drawable.objects_column_solid,
                outlineVector = R.drawable.objects_column
            )
        }
    }
)