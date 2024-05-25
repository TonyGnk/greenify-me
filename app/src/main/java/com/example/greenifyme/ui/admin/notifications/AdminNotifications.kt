package com.example.greenifyme.ui.admin.notifications

import android.app.Activity
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedColumn

/**
 * Displays the admin notifications screen.(AVAILABLE SOON)
 * This screen will hold all the notifications for the admin.
 * Including new forms and new subscriptions
 */
@Composable
@Preview
fun AdminNotifications() {
    val context = LocalContext.current as Activity

    SharedColumn(applyHorizontalPadding = false) {
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
        Text("The screen will be ready by Tuesday @TonyGnk")
    }
}
