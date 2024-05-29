package com.example.greenifyme.ui.admin.home

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.ui.admin.materials.AdminMaterialsActivity
import com.example.greenifyme.ui.admin.notifications.AdminNotificationsActivity
import com.example.greenifyme.ui.shared.SharedAppBar

/**
 * This composable function displays an app bar for the admin home screen with animated text,
 * a notifications icon that opens the AdminNotificationsActivity, and an exit icon to close the activity.
 *
 * @param text The label text displayed in the app bar. Default is "Label".
 */
@Composable
@Preview
fun AdminHomeAppBar(text: String = "Label") {
    val context = LocalContext.current as Activity

    SharedAppBar(
        isTextAnimated = true,
        text = text,
    ) {
        IconButton(
            onClick = {
                context.startActivity(
                    Intent(context, AdminNotificationsActivity::class.java)
                ) // Opens the notifications screen
            },
            content = {
                Icon(
                    painter = getVector(R.drawable.bell),
                    contentDescription = getString(R.string.admin_home_app_bar_notifications),
                    modifier = Modifier.size(22.dp)
                )
            }
        )
        IconButton(
            onClick = {
                context.startActivity(
                    Intent(context, AdminMaterialsActivity::class.java)
                ) // Opens the notifications screen
            },
            content = {
                Icon(
                    painter = getVector(R.drawable.file_edit),
                    contentDescription = getString(R.string.admin_home_app_bar_edit_list),
                    modifier = Modifier.size(22.dp)
                )
            }
        )
        IconButton(
            onClick = { context.finish() },
            content = {
                Icon(
                    painter = getVector(R.drawable.exit),
                    contentDescription = getString(R.string.admin_home_app_bar_exit),
                    modifier = Modifier.size(22.dp)
                )
            }
        )
    }
}