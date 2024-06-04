package com.example.greenifyme.ui.admin.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.SharedModelProviderWithNotification
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.data.form.toFormNotification

class AdminNotificationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val useSampleData = intent.getBooleanExtra("UseSampleData", false)
        val formNotificationBundle = intent.getBundleExtra("FormNotification")
        val formNotification = formNotificationBundle?.toFormNotification()

        setContent {
            ComposeTheme {
                AdminNotificationsScreen(
                    viewModel(
                        factory = SharedModelProviderWithNotification.Factory(
                            useSampleData, formNotification
                        )
                    )
                )
            }
        }
    }
}