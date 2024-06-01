package com.example.greenifyme

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.greenifyme.data.GreenDatabase
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ApplicationSetup : Application() {
    lateinit var greenRepository: GreenRepository

    override fun onCreate() {
        //DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()

        // Load the account repository
        greenRepository = GreenRepository(GreenDatabase.getDatabase(this).dao())

        //Notifications area
        val notificationChannel = getNotificationChannelId(this)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.create(notificationChannel)
    }
}

fun getNotificationChannelId(context: Context): NotificationChannel =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel(
            "notification_channel_id",
            context.getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
    } else {
        TODO("VERSION.SDK_INT < O")
    }

fun NotificationManager.create(
    notificationChannel: NotificationChannel
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.createNotificationChannel(notificationChannel)
    }
}

fun getScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)