package com.example.greenifyme.data.form

import android.os.Bundle
import com.example.greenifyme.ui.admin.notifications.NotificationItem

fun NotificationItem.FormNotification.toBundle() = Bundle().apply {
    putLong("createdAt", createdAt)
    putBoolean("hasAdminViewed", hasViewed)
    putString("accountName", accountName)
    putInt("accountId", accountId)
    putInt("formId", formId)
}


fun Bundle.toFormNotification(): NotificationItem.FormNotification =
    NotificationItem.FormNotification(
        createdAt = getLong("createdAt"),
        hasViewed = getBoolean("hasAdminViewed"),
        accountName = getString("accountName", ""),
        accountId = getInt("accountId"),
        formId = getInt("formId")
    )