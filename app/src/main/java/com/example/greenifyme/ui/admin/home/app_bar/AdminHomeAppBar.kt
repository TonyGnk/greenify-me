package com.example.greenifyme.ui.admin.home.app_bar

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.model.AdminHomeAppBarModel
import com.example.greenifyme.ui.admin.home.model.AdminHomeState
import com.example.greenifyme.ui.admin.notifications.AdminNotificationsActivity

@Composable
fun AdminHomeAppBar(
    model: AdminHomeAppBarModel = AdminHomeAppBarModel(),
    state: AdminHomeState = AdminHomeState(),
    horizontalPadding: Dp = 12.dp
) {
    val context = LocalContext.current as Activity

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        AnimatedGreeting(state.greetingText)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                val intent = Intent(context, AdminNotificationsActivity::class.java)
                context.startActivity(intent)
            },
            content = {
                Icon(
                    //The most common icons are available in the Icons class
                    Icons.Outlined.Notifications,
                    //This contentDescription is for accessibility
                    contentDescription = "Notifications"
                )
            }
        )
        IconButton(
            onClick = {
                context.finish()
            },
            content = {
                Icon(
                    painterResource(id = R.drawable.baseline_exit_to_app_24),
                    contentDescription = "Exit"
                )
            }
        )
    }
}


@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        AdminHomeAppBar()
    }
}