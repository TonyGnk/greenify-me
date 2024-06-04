package com.example.greenifyme

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.greenifyme.data.GreenDatabase
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.NormalDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ApplicationSetup : Application() {
    lateinit var normalRepository: GreenRepository
    lateinit var sampleRepository: GreenRepository

    override fun onCreate() {
        //DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()

        normalRepository = GreenRepository(GreenDatabase.getDatabase(this).dao())
        sampleRepository = GreenRepository(NormalDatabase.getDatabase(this).dao())

        setupNotifications()
    }

    private fun setupNotifications() {
        val notificationChannel = createNotificationChannel(this)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (notificationChannel != null) {
            notificationManager.createNotificationChannelIfNeeded(notificationChannel)
        }
    }
}

/**
 * Creates a NotificationChannel if the Android version supports it.
 * @Param context The application context
 * @return The NotificationChannel object
 */
fun createNotificationChannel(context: Context): NotificationChannel? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel(
            "notification_channel_id",
            context.getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
    } else {
        null
    }

/**
 * Extension function to create a notification channel if the version is supported.
 * @Param notificationChannel The NotificationChannel object to be created
 */
fun NotificationManager.createNotificationChannelIfNeeded(notificationChannel: NotificationChannel) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.createNotificationChannel(notificationChannel)
    }
}

/**
 * Provides a CoroutineScope with the IO dispatcher.
 * @return The CoroutineScope object
 */
fun getScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)