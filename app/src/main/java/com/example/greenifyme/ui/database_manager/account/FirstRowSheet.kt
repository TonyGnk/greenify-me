package com.example.greenifyme.ui.database_manager.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.ui.database_manager.content_shared.bottom_sheet.SheetTextField
import com.example.greenifyme.ui.database_manager.content_shared.model.AccountState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState

@Composable
fun FirstRowSheet(
    state: ContentUiState,
    itemState: AccountState,
    onItemSave: () -> Unit,
    onValueChange: (AccountState) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SheetTextField(
            value = itemState.name,
            onValueChange = { onValueChange(itemState.copy(name = it)) },
            label = state.strings.nameField,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_badge_24),
                    contentDescription = null,
                )
            },
            modifier = Modifier.weight(1f)
        )
        ChoiceSegmented(
            isEnable = itemState.isAdmin,
            onValueChange = {
                onValueChange(itemState.copy(isAdmin = !itemState.isAdmin))
            },
            iconResource = R.drawable.outline_admin_panel_settings_24,
            iconDescription = R.string.account_is_admin
        )
        Button(
            onClick = { onItemSave() },
            modifier = Modifier.height(49.dp)
        ) { Text("Save") }

    }
}

@Composable
fun ChoiceSegmented(
    isEnable: Boolean,
    onValueChange: () -> Unit,
    iconResource: Int,
    iconDescription: Int
) {
    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = if (isEnable) MaterialTheme.colorScheme.primaryContainer
            else Color.Transparent,
        ),
        modifier = Modifier
            .height(48.dp)
            .width(48.dp),
        onClick = { onValueChange() }) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = stringResource(id = iconDescription),
            modifier = Modifier.padding(12.dp)
        )
    }
}