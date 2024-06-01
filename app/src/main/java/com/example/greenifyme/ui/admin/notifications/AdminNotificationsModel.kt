package com.example.greenifyme.ui.admin.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.relations.TrackWithMaterial
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminNotificationsModel(val repository: GreenRepository) : ViewModel() {
    val state2 = MutableStateFlow(AdminNotificationState2())
    val tracksFlow = MutableStateFlow(listOf<TrackWithMaterial>())

    private val formsFlow = repository.getFormsWithAccountName()
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

    fun onNotificationClicked(item: NotificationItem) {
        state2.update {
            it.copy(
                selectedNotification = item,
                modalVisible = true
            )
        }
        if (item is NotificationItem.FormNotification) {
            viewModelScope.launch {
                repository.getTracksWithMaterial(formId = item.formId).collect { tracks ->
                    tracksFlow.update {
                        tracks
                    }
                }
            }
        }
    }

    fun setFormViewed() {
        val notificationForm =
            state2.value.selectedNotification as NotificationItem.FormNotification
        repository.updateFormHasAdminViewed(notificationForm.formId, true, viewModelScope)
    }

    fun onDismissRequest() {
        viewModelScope.launch {
            state2.update { it.copy(modalVisible = false) }
            delay(400)
            state2.update { it.copy(selectedNotification = null) }
        }
    }

}