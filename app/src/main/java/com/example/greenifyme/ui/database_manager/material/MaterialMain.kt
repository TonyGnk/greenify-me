package com.example.greenifyme.ui.database_manager.material

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.database_manager.content_shared.ContentScaffold

@Composable
fun MaterialMain(
	model : MaterialModel = viewModel(
		factory = ViewModelProvider.Factory
	)
) {
	val databaseItems by model.databaseItems.collectAsState()
	val materialUiState by model.uiState.collectAsState()
	ContentScaffold(model, databaseItems, materialUiState)
}


