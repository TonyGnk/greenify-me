package com.example.greenifyme.ui.database_manager.account.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.data.account.Account
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountModel

@Composable
fun AccountList(
    model: AccountModel,
    accountItems: List<Account>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 78.dp),
    ) {
        items(items = accountItems, key = { it.id }) { item ->
            AccountListItem(
                item = item,
                onItemClick = { model.onItemClick(item) },
                onItemDelete = { model.deleteItem(it) }
            )
        }
    }
}

@Composable
fun AccountListItem(
    item: Account,
    onItemClick: () -> Unit,
    onItemDelete: (Account) -> Unit,
) {
    ListItem(
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .fillMaxWidth()
            .height(40.dp),
        headlineContent = {
            AccountListItemContent(account = item, onAccountDelete = onItemDelete)
        },
    )
}

@Composable
fun AccountListItemContent(account: Account, onAccountDelete: (Account) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = account.id.toString() + "     "
                    + account.name + " | " + account.email,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (account.isAdmin) FontWeight.W900 else FontWeight.W300,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { onAccountDelete(account) },
            modifier = Modifier
                .width(32.dp)
                .height(32.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription =
                stringResource(id = R.string.delete)
            )
        }
    }
}