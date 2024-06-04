package com.example.greenifyme.compose_utilities

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.greenifyme.R
import com.example.greenifyme.data.form.toBundle
import com.example.greenifyme.ui.admin.notifications.AdminNotificationsActivity
import com.example.greenifyme.ui.admin.notifications.NotificationItem
import kotlin.random.Random


class NewFormNotification : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            "notification_channel_id",
            "Notification name",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
    }
}

class NotificationHandler(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = "notification_channel_id"

    fun showNewFormNotification(
        text: String = "",
        useSampleData: Boolean,
        formNotification: NotificationItem.FormNotification
    ) {
        val intent = Intent(context, AdminNotificationsActivity::class.java).apply {
            putExtra("UseSampleData", useSampleData)
            putExtra("FormNotification", formNotification.toBundle())
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            Random.nextInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle(context.getString(R.string.notification_channel_form_title))
            .setContentText(text)
            .setSmallIcon(R.drawable.baseline_receipt_long_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }
}