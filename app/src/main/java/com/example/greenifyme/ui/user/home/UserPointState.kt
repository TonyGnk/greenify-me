package com.example.greenifyme.ui.user.home

import com.example.greenifyme.R

data class UserPointState(
    val points: Int = 12590,
) {
    val targetingLevel: UserLevels = when (points) {
        in 0..<UserLevel1.points -> UserLevel1
        in UserLevel1.points..<UserLevel2.points -> UserLevel2
        else -> UserLevel3
    }
    val pointsInLevel: Int = when (targetingLevel) {
        is UserLevel1 -> points
        is UserLevel2 -> points - UserLevel1.points
        is UserLevel3 -> points - UserLevel2.points
    }
    val targetPointsInLevel: Int = when (targetingLevel) {
        is UserLevel1 -> UserLevel1.points
        is UserLevel2 -> UserLevel2.points - UserLevel1.points
        is UserLevel3 -> UserLevel3.points - UserLevel2.points
    }
    val percentInLevel: Float = pointsInLevel.toFloat() / targetPointsInLevel.toFloat()
    //val listOfLevels: List<UserLevels> = listOf(UserLevel1, UserLevel2, UserLevel3)
}

sealed class UserLevels {
    abstract val order: Int
    abstract val points: Int
    abstract val levelNameResource: Int
}

private data object UserLevel1 : UserLevels() {
    override val order: Int = 1
    override val points: Int = 1000
    override val levelNameResource: Int = R.string.admin_city_level_1
}

private data object UserLevel2 : UserLevels() {
    override val order: Int = 2
    override val points: Int = 2500
    override val levelNameResource: Int = R.string.admin_city_level_2
}

private data object UserLevel3 : UserLevels() {
    override val order: Int = 3
    override val points: Int = 5000
    override val levelNameResource: Int = R.string.admin_city_level_3
}