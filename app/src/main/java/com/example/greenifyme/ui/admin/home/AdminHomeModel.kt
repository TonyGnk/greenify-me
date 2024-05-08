package com.example.greenifyme.ui.admin.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar

//Use this for

class AdminHomeModel : ViewModel() {

    val adminHomeState = MutableStateFlow(AdminHomeState(getGreetingTextFromTime()))

    fun getGreetingTextFromTime(): String {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        return when (currentHour) {
            in 6..11 -> "Καλημέρα"
            else -> "Καλησπέρα"
        }
    }

}

data class AdminHomeState(
    val greetingText: String
)

