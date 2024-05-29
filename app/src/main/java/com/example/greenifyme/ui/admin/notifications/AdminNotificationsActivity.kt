package com.example.greenifyme.ui.admin.notifications

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
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
    val listOfNotificationNew = listOf(
        Notification(NotificationType.FORM, "New Form", "A new form has been submitted"),
        Notification(
            NotificationType.SUBSCRIPTION,
            "New Subscription",
            "A new subscription has been made"
        ),
        Notification(NotificationType.FORM, "New Form", "A new form has been submitted"),
        Notification(
            NotificationType.SUBSCRIPTION,
            "New Subscription",
            "A new subscription has been made"
        ),
        Notification(NotificationType.FORM, "New Form", "A new form has been submitted"),
        Notification(
            NotificationType.SUBSCRIPTION,
            "New Subscription",
            "A new subscription has been made"
        ),
    )
    val listOfNotification = listOf(
        Notification(NotificationType.FORM, "New Form", "A new form has been submitted"),
        Notification(
            NotificationType.SUBSCRIPTION,
            "New Subscription",
            "A new subscription has been made"
        ),
        Notification(NotificationType.FORM, "New Form", "A new form has been submitted"),
        Notification(
            NotificationType.SUBSCRIPTION,
            "New Subscription",
            "A new subscription has been made"
        ),
        Notification(NotificationType.FORM, "New Form", "A new form has been submitted"),
        Notification(
            NotificationType.SUBSCRIPTION,
            "New Subscription",
            "A new subscription has been made"
        ),
        Notification(NotificationType.FORM, "New Form", "A new form has been submitted"),
        Notification(
            NotificationType.SUBSCRIPTION,
            "New Subscription",
            "A new subscription has been made"
        ),
        Notification(NotificationType.FORM, "New Form", "A new form has been submitted"),
        Notification(
            NotificationType.SUBSCRIPTION,
            "New Subscription",
            "A new subscription has been made"
        ),
    )


    SharedColumn(
        applyHorizontalPadding = true,
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
        LazyColumn {
            item { Text(text = "New") }

            items(listOfNotificationNew) {
                when (it.type) {
                    NotificationType.FORM -> FormListItem()
                    NotificationType.SUBSCRIPTION -> SubscriptionListItem()
                }
            }

            item { Text(text = "Older") }

            items(listOfNotification) {
                when (it.type) {
                    NotificationType.FORM -> FormListItem()
                    NotificationType.SUBSCRIPTION -> SubscriptionListItem()
                }
            }

        }
    }

}

enum class NotificationType {
    FORM, SUBSCRIPTION
}

data class Notification(
    val type: NotificationType,
    val title: String,
    val description: String
)

@Composable
private fun FormListItem() {
    ListItem(headlineContent = { Text("New Form") })
}

@Composable
private fun SubscriptionListItem() {
    ListItem(headlineContent = { Text("New Subscription") })
}
