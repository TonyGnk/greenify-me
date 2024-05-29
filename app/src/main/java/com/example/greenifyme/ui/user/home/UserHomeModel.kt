package com.example.greenifyme.ui.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
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
) : ViewModel() {

    val state = MutableStateFlow(UserHomeState())
    val tipState = MutableStateFlow(TipState())

    init {
        setRandomTip()
        if (!greetingAnimationPlayedUser) {

            viewModelScope.launch {
                delay(400)
                getGreetingTextFromTime()
            }
            viewModelScope.launch {
                delay(2300)
                state.update {
                    it.copy(greetingText = R.string.app_name)
                }
                greetingAnimationPlayedUser = true
            }
        }
    }

    private fun setRandomTip() {
        val randomIndex = tipList.indices.random()
        setTip(randomIndex)
    }

    private fun setTip(index: Int) {
        tipState.update {
            it.copy(
                selectedTip = tipList[index]
            )
        }
    }

    private fun getGreetingTextFromTime() {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        val greetingText = when (currentHour) {
            in 6..11 -> R.string.admin_good_morning
            else -> R.string.admin_good_afternoon
        }
        state.update {
            it.copy(greetingText = greetingText)
        }
    }
}

var greetingAnimationPlayedUser: Boolean = false

data class UserHomeState(
    val greetingText: Int = if (greetingAnimationPlayedUser) R.string.app_name else R.string.empty,
)