package com.example.greenifyme.ui.admin.notifications

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.ViewModelProvider
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedColumn

class AdminNotificationsActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //Apply default style and colors
            ComposeTheme {
                AdminNotifications()
            }
        }
    }
}

/**
 * Displays the admin notifications screen.(AVAILABLE SOON)
 * This screen will hold all the notifications for the admin.
 * Including new forms and new subscriptions
 */
@Composable
@Preview
private fun AdminNotifications() {
    val context = LocalContext.current as Activity
    val model: AdminNotificationsModel = viewModel(factory = ViewModelProvider.Factory)
    val state = model.state.collectAsState()
    val listOfNotification = state.value.olderList
    val listOfNotificationNew = state.value.todayList

    SharedColumn(
        applyHorizontalPadding = false,
    ) {
        SharedAppBar(
            text = "Notifications",
            isBackButtonVisible = true,
            onBackButtonPressed = { context.finish() }
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = getVector(drawableValue = R.drawable.check_double),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
        LazyColumn(
            modifier = Modifier.clip(RoundedCornerShape(26.dp))
        ) {
            item {
                Text(
                    text = "Today",
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp)
                )
            }

            items(listOfNotificationNew) {
                val type: CornersType = when (listOfNotificationNew.indexOf(it)) {
                    0 -> CornersType.FIRST
                    listOfNotificationNew.size - 1 -> CornersType.LAST
                    else -> CornersType.MIDDLE
                }
                when (it.type) {
                    NotificationType.FORM -> FormListItem(it, type)
                    NotificationType.SUBSCRIPTION -> SubscriptionListItem(it, type)
                }
            }

            item {
                Text(
                    text = "Older",
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp)
                )
            }

            items(listOfNotification) {
                val type: CornersType = when (listOfNotification.indexOf(it)) {
                    0 -> CornersType.FIRST
                    listOfNotificationNew.size - 1 -> CornersType.LAST
                    else -> CornersType.MIDDLE
                }
                when (it.type) {
                    NotificationType.FORM -> FormListItem(it, type)
                    NotificationType.SUBSCRIPTION -> SubscriptionListItem(it, type)
                }

            }

        }
    }

}

@Composable
private fun FormListItem(item: NotificationListItem, type: CornersType) {
    ListItem(
        headlineContent = {
            Text(
                " Form with id ${item.formId} has been submitted",
            )
        },
        modifier = Modifier.clip(
            when (type) {
                CornersType.FIRST -> RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)
                CornersType.LAST -> RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp)
                CornersType.MIDDLE -> RectangleShape
            }
        )
    )
}

@Composable
private fun SubscriptionListItem(item: NotificationListItem, type: CornersType) {
    ListItem(
        headlineContent = {
            Text(
                " ${item.accountName ?: "Unknown"} has registered",
            )
        },
        modifier = Modifier.clip(
            when (type) {
                CornersType.FIRST -> RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                CornersType.LAST -> RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                CornersType.MIDDLE -> RectangleShape
            }
        )
    )
}

enum class CornersType {
    FIRST,
    LAST,
    MIDDLE
}