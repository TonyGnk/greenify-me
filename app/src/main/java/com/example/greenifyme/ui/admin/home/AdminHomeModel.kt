package com.example.greenifyme.ui.admin.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar

//Use this for

class AdminHomeModel : ViewModel() {

    //Copy-paste this 2 lines
    //We do that for safety, the viewmodel handles the _adminHomeState and
    //the fragment rebuilds only for the adminHomeState (public)
    // Don't touch the adminHomeState here, only the _adminHomeState
    private val _adminHomeState = MutableStateFlow(AdminHomeState(getGreetingTextFromTime()))
    val adminHomeState: StateFlow<AdminHomeState> = _adminHomeState

    private fun getGreetingTextFromTime(): String {
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

