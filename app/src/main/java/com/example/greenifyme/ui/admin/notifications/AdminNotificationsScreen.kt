package com.example.greenifyme.ui.admin.notifications

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.ui.shared.SharedAnimatedList
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedBackBehavior
import com.example.greenifyme.ui.shared.SharedColumn

@Composable
fun AdminNotificationsScreen(viewModel: AdminNotificationsModel) {
    val activity = LocalContext.current as Activity
    val state = viewModel.state.collectAsState()
    val modalState = viewModel.state2.collectAsState()
    val tracks = viewModel.tracksFlow.collectAsState()

    SharedColumn(applyHorizontalPadding = false) {
        SharedAppBar(
            text = stringResource(R.string.notifications),
            backBehavior = SharedBackBehavior.Enable {
                activity.finish()
            },
        )

        SharedAnimatedList(visible = state.value.olderList.isNotEmpty() || state.value.todayList.isNotEmpty()) {
            NotificationList(
                newNotifications = state.value.todayList,
                oldNotifications = state.value.olderList,
                onNotificationClick = { viewModel.onNotificationClicked(it) }
            )
        }
    }

    if (modalState.value.modalVisible) {
        ContentDialog(
            item = modalState.value.selectedNotification!!,
            onDismissRequest = { viewModel.onDismissRequest() },
            setFormViewed = { viewModel.setFormViewedAddPoints() },
            tracks = tracks.value
        )
    }
}

@Composable
fun NotificationList(
    newNotifications: List<NotificationItem>,
    oldNotifications: List<NotificationItem>,
    onNotificationClick: (NotificationItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.clip(
            RoundedCornerShape(getDimen(R.dimen.column_card_corner_radius))
        )
    ) {
        if (newNotifications.isNotEmpty()) {
            item {
                Text(
                    text = "Today",
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp)
                )
            }
            items(items = newNotifications) { item ->
                val type = findCornersType(newNotifications, item)
                NotificationListItem(item, type) { onNotificationClick(item) }
            }
        }

        if (newNotifications.isNotEmpty() && oldNotifications.isNotEmpty()) {
            item {
                Text(
                    text = "Older",
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp)
                )
            }
        }

        items(oldNotifications) { item ->
            val type = findCornersType(oldNotifications, item)
            NotificationListItem(item, type) { onNotificationClick(item) }
        }
    }
}

private fun findCornersType(list: List<NotificationItem>, item: NotificationItem): CornersType {
    return if (list.size == 1) CornersType.BOTH
    else when (list.indexOf(item)) {
        0 -> CornersType.FIRST
        list.size - 1 -> CornersType.LAST
        else -> CornersType.MIDDLE
    }
}