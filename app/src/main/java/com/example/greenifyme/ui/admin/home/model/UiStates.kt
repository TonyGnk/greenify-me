package com.example.greenifyme.ui.admin.home.model

import com.example.greenifyme.R


data class AdminHomeState(
    val greetingText: Int = if (greetingAnimationHasPlayedOnce) R.string.app_name else R.string.empty,
)

data class AdminTipState(
    val selectedTip: Int = R.string.empty,
)

sealed class CityLevels {
    abstract val levelName: Int
    abstract val points: Int
    abstract val image: Int
}

data class CityLevel1(override val points: Int) : CityLevels() {
    override val levelName = R.string.admin_city_level_1
    override val image = R.drawable.city_level_1
}

data class CityLevel2(override val points: Int) : CityLevels() {
    override val levelName = R.string.admin_city_level_2
    override val image = R.drawable.city_level_2
}

data class CityLevel3(override val points: Int) : CityLevels() {
    override val levelName = R.string.admin_city_level_3
    override val image = R.drawable.city_level_3
}
