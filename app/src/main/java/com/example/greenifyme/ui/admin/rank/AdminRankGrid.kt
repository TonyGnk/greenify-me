package com.example.greenifyme.ui.admin.rank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.data.Account

/**
 * Displays a grid of admin ranks using a LazyColumn.
 *
 * @param accountList List of accounts to display.
 */
@Composable
fun AdminRankGrid(accountList: List<Account>) {
    LazyColumn(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.surfaceContainerLowest,
            shape = RoundedCornerShape(getDimen(R.dimen.column_card_corner_radius))
        ),
        state = rememberLazyListState(initialFirstVisibleItemIndex = 0)
    ) {
        itemsIndexed(items = accountList) { index, item ->
            AccountListItem(
                item = item,
                index = index
            )
        }
    }
}

/**
 * Displays an item in the admin rank list.
 *
 * @param item The account to display.
 * @param index The position of the item in the list.
 */
@Composable
@Preview
private fun AccountListItem(
    item: Account = Account(2, "John Doe"),
    index: Int = 3
) {
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent,
        ),
        headlineContent = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = getDimen(dimenValue = R.dimen.horizontalScreenPadding))
            ) {
                Text(item.name, style = MaterialTheme.typography.titleMedium)
                when (index) {
                    0 -> Icon(
                        painter = getVector(drawableValue = R.drawable.trophy_star),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(25.dp),
                    )

                    else -> Text(
                        "#${index + 1}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}