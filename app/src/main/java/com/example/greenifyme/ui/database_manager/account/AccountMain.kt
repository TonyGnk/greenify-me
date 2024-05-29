package com.example.greenifyme.ui.database_manager.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.ViewModelProvider
import com.example.greenifyme.ui.database_manager.content_shared.ContentScaffold

@Composable
fun AccountMain(
    model: AccountModel = viewModel(
        factory = ViewModelProvider.Factory
    )
) {
    val databaseItems by model.databaseItems.collectAsState()
    val accountUiState by model.uiState.collectAsState()
    ContentScaffold(model, databaseItems, accountUiState)
}