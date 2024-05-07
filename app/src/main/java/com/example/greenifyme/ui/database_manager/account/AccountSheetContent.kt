package com.example.greenifyme.ui.database_manager.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.database_manager.DBManagerNavDestination
import com.example.greenifyme.ui.database_manager.content_shared.model.AccountState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState

@Composable
fun AccountSheetContent(
    state: ContentUiState,
    updateUiState: (AccountState) -> Unit,
    onItemSave: () -> Unit,
    accountState: AccountState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FirstRowSheet(
            state = state,
            itemState = accountState,
            onItemSave = onItemSave,
            onValueChange = updateUiState,
        )
        ItemInputForm(
            itemDetails = accountState,
            onValueChange = updateUiState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
fun Preview() {
    AccountSheetContent(
        state = ContentUiState(DBManagerNavDestination.Record, selectedAccount = null),
        updateUiState = {},
        onItemSave = {},
        accountState = AccountState(),
    )
}