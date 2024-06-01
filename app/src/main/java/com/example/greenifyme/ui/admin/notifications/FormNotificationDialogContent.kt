package com.example.greenifyme.ui.admin.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.data.relations.TrackWithMaterial
import com.example.greenifyme.ui.database_manager.content_shared.model.getFullTimeFromEpoch

@Composable
fun FormNotificationDialogContent(
    item: NotificationItem.FormNotification,
    tracks: List<TrackWithMaterial>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Information",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(getDimen(R.dimen.column_card_corner_radius)))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(14.dp)
        ) {
            Row {
                Icon(
                    painter = getVector(drawableValue = R.drawable.user),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(19.dp)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = item.accountName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Row {
                Icon(
                    painter = getVector(drawableValue = R.drawable.calendar_lines),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(19.dp)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = getFullTimeFromEpoch(item.createdAt),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Row {
                Icon(
                    painter = getVector(drawableValue = R.drawable.coins),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(19.dp)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "123 points",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "Tracks (${tracks.size})",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(getDimen(R.dimen.column_card_corner_radius)))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(14.dp)
                .heightIn(max = 160.dp)
        ) {
            items(tracks) {
                Row {
                    Icon(
                        painter = getVector(it.category.icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(19.dp)
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Text(
                        text = it.name,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = it.quantity.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}