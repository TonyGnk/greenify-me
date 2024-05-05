package com.example.greenifyme.ui.database_manager.account.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountFields
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountModel
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountBottomSheet(
    model: AccountModel,
    accountUiState: AccountUiState
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = { model.setBottomSheet(false) },
        windowInsets = BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Bottom),
        sheetState = sheetState,
        dragHandle = { },
    ) {
        SheetContent(
            updateUiState = { model.updateUiState(it) },
            onItemSave = {
                coroutineScope.launch {
                    delay(300)
                    if (accountUiState.openSheetForEditing) model.updateItem()
                    else model.saveItem()
                    sheetState.hide()
                    model.setBottomSheet(false)
                }
            },
            sheetUiState = accountUiState
        )
    }
}

@Composable
private fun SheetContent(
    updateUiState: (AccountFields) -> Unit,
    onItemSave: () -> Unit,
    sheetUiState: AccountUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FirstRowSheet(
            itemDetails = sheetUiState.accountFields,
            onItemSave = onItemSave,
            onValueChange = updateUiState,
        )
        ItemInputForm(
            itemDetails = sheetUiState.accountFields,
            onValueChange = updateUiState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
@Preview
fun Preview() {
    SheetContent(
        updateUiState = {},
        onItemSave = {},
        sheetUiState = AccountUiState(selectedAccount = null),
    )
}