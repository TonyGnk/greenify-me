package com.example.greenifyme.ui.database_manager.material

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.MaterialState

@Composable
fun MaterialSheetContent(
    state: ContentUiState,
    updateUiState: (MaterialState) -> Unit,
    onItemSave: () -> Unit,
    materialState: MaterialState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FirstRowSheet(
            state = state,
            itemState = materialState,
            onItemSave = onItemSave,
            onValueChange = updateUiState,
        )
//        ItemInputForm(
//            itemDetails = accountState,
//            onValueChange = updateUiState,
//            modifier = Modifier.fillMaxWidth()
//        )
    }
}

@Composable
@Preview
fun Preview() {
    MaterialSheetContent(
        state = ContentUiState(DBManagerNavDestination.Record, selectedAccount = null),
        updateUiState = {},
        onItemSave = {},
        materialState = MaterialState(),
    )
}