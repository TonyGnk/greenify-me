package com.example.greenifyme.ui.database_manager.track

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.ViewModelProvider
import com.example.greenifyme.ui.database_manager.content_shared.ContentScaffold

@Composable
fun TrackMain(
    model: TrackModel = viewModel(
        factory = ViewModelProvider.Factory
    )
) {
    val databaseItems by model.databaseItems.collectAsState()
    val trackUiState by model.uiState.collectAsState()
    ContentScaffold(model, databaseItems, trackUiState)
}