package com.example.greenifyme.ui.admin.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AdminNotificationsModel(repository: GreenRepository) : ViewModel() {


    private val formsFlow = repository.getForms()
        .map { AdminNotificationState(forms = it) }

    private val accountsFlow = repository.getAccounts()
        .map { AdminNotificationState(accounts = it) }

    val state = combine(formsFlow, accountsFlow) { formsState, accountsState ->
        AdminNotificationState(
            forms = formsState.forms,
            accounts = accountsState.accounts
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = AdminNotificationState(
            forms = emptyList(),
            accounts = emptyList()
        )
    )
}

data class AdminNotificationState(
    val forms: List<Form> = emptyList(),
    val accounts: List<Account> = emptyList(),

    val isLoading: Boolean = false,
    val error: String = ""
) {

    private val listOfNewForms: List<NotificationListItem> =
        forms.map { it.toNotificationListItem() }

    private val listOfNewAccounts: List<NotificationListItem> =
        accounts.map { it.toNotificationListItem() }

    private val combinedList: List<NotificationListItem>
        get() = (listOfNewForms + listOfNewAccounts).sortedBy { it.createdAt }


    val todayList =
        combinedList.filter { it.createdAt > System.currentTimeMillis() - 24 * 60 * 60 * 1000 }

    val olderList = combinedList - todayList.toSet()
}

data class NotificationListItem(
    val accountName: String? = null,
    val type: NotificationType = NotificationType.FORM,
    val createdAt: Long,
    val formId: Int = 1,
)

fun Form.toNotificationListItem(): NotificationListItem {
    return NotificationListItem(
        type = NotificationType.FORM,
        createdAt = this.createdAt,
        formId = this.formId
    )
}

fun Account.toNotificationListItem() = NotificationListItem(
    accountName = this.name,
    type = NotificationType.SUBSCRIPTION,
    createdAt = this.createdAt
)

enum class NotificationType {
    FORM, SUBSCRIPTION
}