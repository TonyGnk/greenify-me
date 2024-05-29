package com.example.greenifyme.ui.admin.rank


import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedColumn

class AdminRankActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                AdminRank()
            }
        }
    }
}

/**
 * Displays admin rank list.
 */
@Composable
private fun AdminRank() {
    val model: AdminRankModel = viewModel(factory = ViewModelProvider.Factory)
    val accountList by model.databaseItems.collectAsState()

    SharedColumn(applyHorizontalPadding = false) {
        TopBar(getString(model.label))
        AdminRankGrid(accountList)
    }
}

/**
 * Displays the top app bar with optional back button functionality.
 *
 * @param text The text to display in the app bar.
 */
@Composable
private fun TopBar(text: String = "Rank") {
    val activity = LocalContext.current as Activity

    SharedAppBar(
        text = text,
        isBackButtonVisible = true,
        onBackButtonPressed = { activity.finish() }
    )
}