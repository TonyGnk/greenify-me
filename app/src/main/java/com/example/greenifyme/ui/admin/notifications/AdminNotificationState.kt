package com.example.greenifyme.ui.admin.notifications

import com.example.greenifyme.R
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.Track
import com.example.greenifyme.data.relations.FormWithAccountName

data class AdminNotificationState2(
    val selectedNotification: NotificationItem? = null,
    val tracks: List<Track> = listOf(),
    val modalVisible: Boolean = false,
)

data class AdminNotificationState(
    val forms: List<FormWithAccountName> = emptyList(),
    val accounts: List<Account> = emptyList(),
) {
    private val listOfNewForms: List<NotificationItem> = forms.map { it.toNotification() }
    private val listOfNewAccounts: List<NotificationItem> = accounts.map { it.toNotification() }

    private val combinedList: List<NotificationItem> =
        listOfNewForms.sortedByDescending { it.createdAt }//.take(30)

    private val combinedList2: List<NotificationItem> =
        listOfNewAccounts.sortedByDescending { it.createdAt }.take(10)

    private val combinedFormsFirst = combinedList + combinedList2

    val todayList = combinedFormsFirst.filter {
        it.createdAt > System.currentTimeMillis() - 24 * 60 * 60 * 1000
    }

    val olderList = combinedFormsFirst - todayList.toSet()
}

sealed class NotificationItem {
    abstract val createdAt: Long
    abstract val painter: Int
    abstract val hasViewed: Boolean

    data class AccountNotification(
        override val createdAt: Long,
        override val painter: Int = R.drawable.user,
        override val hasViewed: Boolean = true,
        val name: String,
        val accountId: Int,
    ) : NotificationItem()

    data class FormNotification(
        override val createdAt: Long,
        override val painter: Int = R.drawable.baseline_receipt_long_24,
        override val hasViewed: Boolean = true,
        val accountId: Int,
        val accountName: String,
        val formId: Int,
        val formPoints: Int = 0
    ) : NotificationItem()
}

fun FormWithAccountName.toNotification() = NotificationItem.FormNotification(
    createdAt = this.createdAt,
    hasViewed = this.hasAdminViewed,
    accountName = this.accountName,
    accountId = this.accountId,
    formId = this.formId,
)


fun NotificationItem.FormNotification.toForm() = Form(
    formId = this.formId,
    accountId = 0,
    hasAdminViewed = this.hasViewed,
    createdAt = this.createdAt,
)


fun Account.toNotification() = NotificationItem.AccountNotification(
    createdAt = this.createdAt,
    name = this.name,
    accountId = this.accountId
)