package com.example.greenifyme.ui.admin.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.ui.database_manager.content_shared.model.getTimeFromEpoch

@Composable
fun NotificationListItem(
    item: NotificationItem, type: CornersType, onClick: () -> Unit
) {
    Column {
        ListItem(
            headlineContent = { HeadLineText(item) },
            leadingContent = {
                Icon(
                    painter = getVector(item.painter),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(3.dp)
                )
            },
            trailingContent = {
                Text(
                    text = getTimeFromEpoch(item.createdAt),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = if (!item.hasViewed) FontWeight.W900 else FontWeight.ExtraLight
                )
            },
            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
            modifier = Modifier
                .clip(
                    when (type) {
                        CornersType.FIRST -> RoundedCornerShape(
                            topStart = getDimen(R.dimen.column_card_corner_radius),
                            topEnd = getDimen(R.dimen.column_card_corner_radius),
                        )

                        CornersType.LAST -> RoundedCornerShape(
                            bottomStart = getDimen(R.dimen.column_card_corner_radius),
                            bottomEnd = getDimen(R.dimen.column_card_corner_radius),
                        )

                        CornersType.MIDDLE -> RectangleShape
                    }
                )
                .clickable { onClick() }
        )
        if (type != CornersType.LAST) HorizontalDivider(Modifier.fillMaxWidth())

    }
}

@Composable
private fun HeadLineText(item: NotificationItem) = when (item) {
    is NotificationItem.AccountNotification -> Text(
        text = "${item.name} has registered",
        fontFamily = FontFamily.Default,
        fontWeight = when (item.hasViewed) {
            false -> FontWeight.W800
            true -> FontWeight.W300
        }
    )

    is NotificationItem.FormNotification -> Text(
        text = stringResource(R.string.user_submit_form, item.accountName),
        fontFamily = FontFamily.Default,
        fontWeight = when (item.hasViewed) {
            false -> FontWeight.W800
            true -> FontWeight.W300
        }
    )
}

enum class CornersType { FIRST, MIDDLE, LAST }