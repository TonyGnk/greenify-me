package com.example.greenifyme.ui.database.manager.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.SharedModelProvider
import com.example.greenifyme.data.DataObjectType
import com.example.greenifyme.ui.database.manager.content.ManagerContentScreen
import com.example.greenifyme.ui.database.manager.content.ManagerViewModel
import com.example.greenifyme.ui.shared.SharedColumn

/**
 * Displays the navigation bar and content for the database manager screen.
 *
 * @param useSampleData Indicates whether to use sample data for the view model.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerNavigation(useSampleData: Boolean) {
    val navigationModel = ManagerNavigationModel()
    val navState by navigationModel.state.collectAsState()
    val contentViewModel: ManagerViewModel = viewModel(
        factory = SharedModelProvider.Factory(useSampleData)
    )

    SharedColumn(applyHorizontalPadding = false) {
        val navigationBar: @Composable () -> Unit = {
            NavigationBar(
                containerColor = Color.Transparent,
                tonalElevation = 0.dp,
            ) {
                for (item in navState.navigationList) {
                    val isSelected = navState.destination == item.destination
                    NavigationBarItem(
                        icon = {
                            if (isSelected) item.filledIcon()
                            else item.outlineIcon()
                        },
                        label = { item.label() },
                        selected = isSelected,
                        onClick = {
                            navigationModel.navigateTo(item.destination)
                        }
                    )
                }
            }
        }
        when (navState.destination) {
            DatabaseManagerNavigationDestination.Account -> ManagerContentScreen(
                contentViewModel,
                DataObjectType.ACCOUNT
            ) { navigationBar() }

            DatabaseManagerNavigationDestination.Form -> ManagerContentScreen(
                contentViewModel,
                DataObjectType.FORM
            ) { navigationBar() }

            DatabaseManagerNavigationDestination.Track -> ManagerContentScreen(
                contentViewModel,
                DataObjectType.TRACK
            ) { navigationBar() }

            DatabaseManagerNavigationDestination.Material -> ManagerContentScreen(
                contentViewModel,
                DataObjectType.MATERIAL
            ) { navigationBar() }
        }
    }
}


/**
 * Displays large text centered on the screen.
 *
 * @param text The text to display.
 */
@Composable
fun CenteredLargeText(text: String = "") {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}