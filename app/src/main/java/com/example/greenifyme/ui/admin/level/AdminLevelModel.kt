package com.example.greenifyme.ui.admin.level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AdminLevelModel(repository: GreenRepository) : ViewModel() {

    val state = repository.getTotalPoints()
        .map { CityLevelStep(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CityLevelStep(0)
        )

    val animatedState: MutableStateFlow<Float> = MutableStateFlow(0f)

    init {
        viewModelScope.launch {
            state.collect { city ->
                animatedState.update { city.percentInLevel }
            }
        }
    }
}

data class CityLevelStep(
    val points: Int,
    val targetingLevel: Levels = when (points) {
        in 0..<CityLev1.points -> CityLev1
        in CityLev1.points..<CityLev2.points -> CityLev2
        else -> CityLev3
    },
    val pointsInLevel: Int = when (targetingLevel) {
        is CityLev1 -> points
        is CityLev2 -> points - CityLev1.points
        is CityLev3 -> points - CityLev2.points
    },
    val targetPointsInLevel: Int = when (targetingLevel) {
        is CityLev1 -> CityLev1.points
        is CityLev2 -> CityLev2.points - CityLev1.points
        is CityLev3 -> CityLev3.points - CityLev2.points
    },
    val percentInLevel: Float = pointsInLevel.toFloat() / targetPointsInLevel.toFloat(),
    val listOfLevels: List<Levels> = listOf(CityLev1, CityLev2, CityLev3),
)

sealed class Levels {
    abstract val order: Int
    abstract val levelNameResource: Int
    abstract val points: Int
    abstract val imageResource: Int
}

private data object CityLev1 : Levels() {
    override val order: Int = 1
    override val points: Int = 1000
    override val levelNameResource: Int = R.string.admin_city_level_1
    override val imageResource: Int = R.drawable.city_level_1
}

private data object CityLev2 : Levels() {
    override val order: Int = 2
    override val points: Int = 2500
    override val levelNameResource: Int = R.string.admin_city_level_2
    override val imageResource: Int = R.drawable.city_level_2
}

private data object CityLev3 : Levels() {
    override val order: Int = 3
    override val points: Int = 5000
    override val levelNameResource: Int = R.string.admin_city_level_3
    override val imageResource: Int = R.drawable.city_level_3
}

