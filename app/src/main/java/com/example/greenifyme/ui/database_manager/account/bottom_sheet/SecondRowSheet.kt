package com.example.greenifyme.ui.database_manager.account.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountFields

@Composable
fun ItemInputForm(
    itemDetails: AccountFields,
    modifier: Modifier = Modifier,
    onValueChange: (AccountFields) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.weight(0.6f)
        ) {
            SheetTextField(
                value = itemDetails.email,
                onValueChange = { onValueChange(itemDetails.copy(email = it)) },
                label = R.string.email_req,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_email_24),
                        contentDescription = null,
                    )
                },
            )
        }
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.weight(0.4f)
        ) {
            SheetTextField(
                value = itemDetails.password,
                onValueChange = { onValueChange(itemDetails.copy(password = it)) },
                label = R.string.password_req,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_password_24),
                        contentDescription = null,
                    )
                },
            )
        }
    }
}