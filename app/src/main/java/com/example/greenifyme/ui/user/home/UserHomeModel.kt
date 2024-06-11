package com.example.greenifyme.ui.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.ui.shared.tip_of_day.TipState
import com.example.greenifyme.ui.shared.tip_of_day.tipList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar

class UserHomeModel(
    val repository: GreenRepository,
    val account: Account
) : ViewModel() {

    val state = MutableStateFlow(UserHomeState())
    val tipState = MutableStateFlow(TipState())
    val pointState = MutableStateFlow(UserPointState())
    val animatedState: MutableStateFlow<Float> = MutableStateFlow(0f)


    init {
        tipState.update {
            it.copy(selectedTip = tipList[tipList.indices.random()])
        }
        //if (!greetingAnimationPlayedUser) {
        viewModelScope.launch {
            delay(300)
            getGreetingTextFromTime()
        }
        viewModelScope.launch {
            delay(2300)
            state.update {
                it.copy(greetingText = R.string.app_name)
            }
            //greetingAnimationPlayedUser = true
        }
        // }
        viewModelScope.launch {
            delay(400)  // Adjust this delay as needed
            animatedState.update { pointState.value.percentInLevel }
        }
    }

    private fun getGreetingTextFromTime() {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        val greetingText = when (currentHour) {
            in 6..11 -> R.string.user_good_morning
            else -> R.string.user_good_afternoon
        }
        state.update {
            it.copy(greetingText = greetingText)
        }
    }

    fun whenAccountReceived(account: Account) {
        viewModelScope.launch {
            pointState.update {
                it.copy(points = account.points)
            }
            println("whenAccountReceived")
        }
    }

    fun setShouldShowOnce() {
        state.update {
            it.copy(hasShowedOnce = true)
        }
        val accountWithIntroViewed = account.copy(hasIntroViewed = true)
        repository.update(accountWithIntroViewed, viewModelScope)
    }
}

//var greetingAnimationPlayedUser: Boolean = false

data class UserHomeState(
    val hasShowedOnce: Boolean = false,
    // val greetingText: Int = if (greetingAnimationPlayedUser) R.string.app_name else R.string.empty,
    val greetingText: Int = R.string.empty,

    )