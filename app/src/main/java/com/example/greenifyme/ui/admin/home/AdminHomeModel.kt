package com.example.greenifyme.ui.admin.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

//Use this for

class AdminHomeModel : ViewModel() {

    //Copy-paste this 2 lines
    //We do that for safety, the viewmodel handles the _adminHomeState and
    //the fragment rebuilds only for the adminHomeState (public)
    // Don't touch the adminHomeState here, only the _adminHomeState
    private val _adminHomeState = MutableStateFlow(AdminHomeState())
    val adminHomeState: StateFlow<AdminHomeState> = _adminHomeState

    fun changeGreetingText() {
        val newValue = if (_adminHomeState.value.greetingText == "Good Morning") {
            "Good Evening"
        } else {
            "Good Morning"
        }

        //1 Way to update (Suggest way)
        _adminHomeState.update {
            it.copy(greetingText = newValue)
            //If uiState has more fields with update command it will not be updated
        }

        //2 Way to update
        //_adminHomeState.value = _adminHomeState.value.copy(greetingText = "Good Evening")
    }

    private fun getGreetingTextFromTime(): String {
        //Do some logic here


        //When it is ready change the _adminHomeState variable above to
        // private val _adminHomeState = MutableStateFlow(AdminHomeState(getGreetingTextFromTime()))
        // With this change the value of the AdminHomeState will be take the value from this function
        // and update the greetingText at the beginning of his creation

        return ""
    }

}


//Προτιμάται να συγκεντρώνονται όλες οι μεταβλητές μέσα σε μια Δομή
data class AdminHomeState(
    val greetingText: String = "Good Morning" //Replace this line with the above
    //val greetingText: String
)

