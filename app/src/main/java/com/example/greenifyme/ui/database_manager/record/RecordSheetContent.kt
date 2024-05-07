package com.example.greenifyme.ui.database_manager.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.ui.database_manager.account.ChoiceSegmented
import com.example.greenifyme.ui.database_manager.content_shared.bottom_sheet.SheetTextField
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.RecordState

@Composable
fun RecordSheetContent(
    state: ContentUiState,
    updateUiState: (RecordState) -> Unit,
    onItemSave: () -> Unit,
    recordState: RecordState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RecordFirstRow(
            state = state,
            itemState = recordState,
            onItemSave = onItemSave,
            onValueChange = updateUiState,
        )
//        ItemInputForm(
//            itemDetails = sheetUiState.accountFields,
//            onValueChange = updateUiState,
//            modifier = Modifier.fillMaxWidth()
//        )
    }
}

@Composable
fun RecordFirstRow(
    state: ContentUiState,
    itemState: RecordState,
    onItemSave: () -> Unit,
    onValueChange: (RecordState) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SheetTextField(
            value = itemState.accountId,
            onValueChange = { onValueChange(itemState.copy(accountId = it)) },
            label = state.strings.accountId,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_badge_24),
                    contentDescription = null,
                )
            },
            modifier = Modifier.weight(1f)
        )
        ChoiceSegmented(
            isEnable = !itemState.hasAdminViewed,
            onValueChange = {
                onValueChange(itemState.copy(hasAdminViewed = !itemState.hasAdminViewed))
            },
            iconResource = R.drawable.outline_mark_chat_unread_24,
            iconDescription = state.strings.hasAdminViewed
        )
        Button(
            onClick = { onItemSave() },
            modifier = Modifier.height(49.dp)
        ) { Text(stringResource(state.strings.save)) }

    }
}