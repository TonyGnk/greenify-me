package com.example.greenifyme.ui.database_manager.track

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.TrackState

@Composable
fun TrackSheetContent(
    state: ContentUiState,
    updateUiState: (TrackState) -> Unit,
    onItemSave: () -> Unit,
    accountState: TrackState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
//		FirstRowSheet(
//            state = state,
//            itemState = accountState,
//            onItemSave = onItemSave,
//            onValueChange = updateUiState,
//        )
//		ItemInputForm(
//			itemDetails = accountState,
//			onValueChange = updateUiState,
//			modifier = Modifier.fillMaxWidth()
//		)
    }
}


