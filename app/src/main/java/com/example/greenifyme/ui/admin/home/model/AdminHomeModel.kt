package com.example.greenifyme.ui.admin.home.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.ui.admin.home.tip_of_day.tipList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar


class AdminHomeModel : ViewModel() {

    val state = MutableStateFlow(AdminHomeState())
    val tipState = MutableStateFlow(AdminTipState())

    init {
        setRandomTip()
        if (!greetingAnimationHasPlayedOnce) {

            viewModelScope.launch {
                delay(400)
                getGreetingTextFromTime()
            }
            viewModelScope.launch {
                delay(2300)
                state.update {
                    it.copy(greetingText = R.string.app_name)
                }
                greetingAnimationHasPlayedOnce = true
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

    fun nextTrip() {
        val currentTip = tipState.value.selectedTip
        val nextTip =
            if (currentTip == tipList.last()) tipList.first() else tipList[tipList.indexOf(
                currentTip
            ) + 1]
        setTip(tipList.indexOf(nextTip))
    }

    fun previousTip() {
        val currentTip = tipState.value.selectedTip
        val previousTip =
            if (currentTip == tipList.first()) tipList.last() else tipList[tipList.indexOf(
                currentTip
            ) - 1]
        setTip(tipList.indexOf(previousTip))
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

private var greetingAnimationHasPlayedOnce: Boolean = false

data class AdminHomeState(
    val greetingText: Int = if (greetingAnimationHasPlayedOnce) R.string.app_name else R.string.empty,
)

data class AdminTipState(
    val selectedTip: Int = R.string.empty,
)

