package com.example.greenifyme.ui.admin.home.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.ui.admin.home.tip_of_day.tipList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar


class AdminHomeModel(
    val repository: GreenRepository,
) : ViewModel() {

    val state = MutableStateFlow(AdminHomeState())
    val tipState = MutableStateFlow(AdminTipState())
    val cityLevelState = MutableStateFlow(CityLevel1(23000))

    init {
        setRandomTip()
        setCityLevel()
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

    private fun setCityLevel() {
        // Multiple ways
        // Create a dao to collect all the points of accounts
        // Or here create a loop and add all the points in a variable

        // When the points are X->Update with level1, Z-> Update with level2, Y -> Update with level3
        // The X, Y, Z is unknown for now
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

var greetingAnimationHasPlayedOnce: Boolean = false

