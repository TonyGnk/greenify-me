package com.example.greenifyme.ui.database_manager.account

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.greenifyme.data.Account

@Composable
fun AccountListText(
    account: Account,
    modifier: Modifier
) {
    Text(
        text = account.accountId.toString() + "     "
                + account.name + " | " + account.email,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = if (account.isAdmin) FontWeight.W900 else FontWeight.W300,
        modifier = modifier
    )
}