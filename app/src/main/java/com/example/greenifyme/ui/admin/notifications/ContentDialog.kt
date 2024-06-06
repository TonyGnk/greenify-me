package com.example.greenifyme.ui.admin.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.data.relations.TrackWithMaterial

@Composable
fun ContentDialog(
    item: NotificationItem,
    onDismissRequest: () -> Unit,
    setFormViewed: () -> Unit,
    tracks: List<TrackWithMaterial>
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.surfaceContainer,
                    RoundedCornerShape(getDimen(R.dimen.column_card_corner_radius))
                )
                .padding(16.dp)
        ) {
            Text(
                text = when (item) {
                    is NotificationItem.AccountNotification -> ""
                    is NotificationItem.FormNotification -> getString(stringValue = R.string.admin_form_notification_title)
                },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(14.dp))
            when (item) {
                is NotificationItem.AccountNotification -> Text("")
                is NotificationItem.FormNotification -> FormNotificationDialogContent(item, tracks)
            }
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onDismissRequest) {
                    Text(text = getString(R.string.admin_notifications_dismiss))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    onDismissRequest()
                    setFormViewed()
                }, enabled = !item.hasViewed) {
                    Text(text = getString(R.string.admin_notifications_view))
                }
            }
        }
    }
}