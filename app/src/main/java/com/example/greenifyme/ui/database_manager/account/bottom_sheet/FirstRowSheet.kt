package com.example.greenifyme.ui.database_manager.account.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountFields

@Composable
fun FirstRowSheet(
    itemDetails: AccountFields,
    onItemSave: () -> Unit,
    onValueChange: (AccountFields) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SheetTextField(
            value = itemDetails.name,
            onValueChange = { onValueChange(itemDetails.copy(name = it)) },
            label = R.string.item_name_req,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_badge_24),
                    contentDescription = null,
                )
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChoiceSegmented(
                itemDetails = itemDetails,
                onValueChange = onValueChange
            )
            Button(
                onClick = {
                    keyboardController?.hide()
                    onItemSave()
                },
                modifier = Modifier.height(49.dp)
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
fun ChoiceSegmented(
    itemDetails: AccountFields,
    onValueChange: (AccountFields) -> Unit,
) {
    Surface(
        shape = CircleShape,
        color = if (itemDetails.isAdmin) MaterialTheme.colorScheme.primaryContainer
        else Color.Transparent,
        modifier = Modifier
            .height(49.dp)
            .width(49.dp)
    ) {
        IconButton(onClick = {
            onValueChange(itemDetails.copy(isAdmin = !itemDetails.isAdmin))
        }) {
            Icon(
                painter = painterResource(id = R.drawable.outline_admin_panel_settings_24),
                contentDescription = null,
                modifier = Modifier.padding(12.dp)
            )
        }

    }
}