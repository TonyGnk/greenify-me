package com.example.greenifyme.ui.admin.home.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar


class AdminHomeModel : ViewModel() {

    val adminHomeState = MutableStateFlow(AdminHomeState())

    init {
        if (!greetingAnimationHasPlayedOnce) {

            viewModelScope.launch {
                delay(200)
                getGreetingTextFromTime()
            }
            viewModelScope.launch {
                delay(2200)
                adminHomeState.update {
                    it.copy(greetingText = R.string.app_name)
                }
                greetingAnimationHasPlayedOnce = true
            }
        }
    }

    private fun getGreetingTextFromTime() {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        val greetingText = when (currentHour) {
            in 6..11 -> R.string.admin_good_morning
            else -> R.string.admin_good_afternoon
        }
        adminHomeState.update {
            it.copy(greetingText = greetingText)
        }
    }


}

private var greetingAnimationHasPlayedOnce: Boolean = false

data class AdminHomeState(
    val greetingText: Int = if (greetingAnimationHasPlayedOnce) R.string.app_name else R.string.empty,
)

