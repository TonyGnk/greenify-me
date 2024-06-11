package com.example.greenifyme.ui.admin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.data.relations.CategoryQuantitySum
import com.example.greenifyme.data.relations.WinnerItem
import com.example.greenifyme.ui.admin.level.CityLevelStep
import com.example.greenifyme.ui.shared.tip_of_day.TipState
import com.example.greenifyme.ui.shared.tip_of_day.tipList
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import kotlin.enums.EnumEntries


class AdminHomeModel(val repository: GreenRepository) : ViewModel() {

    val state = MutableStateFlow(AdminHomeState())
    val tipState = MutableStateFlow(TipState())

    val levelState = repository.getTotalPoints()
        .map { CityLevelStep(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CityLevelStep(0)
        )

    val animatedCityLevelInt: MutableStateFlow<Int> = MutableStateFlow(0)
    val animatedCityLevel: MutableStateFlow<Float> = MutableStateFlow(0f)
    val label: Int = R.string.admin_quantity_chart_label
    val chartProducerState = MutableStateFlow(CartesianChartModelProducer.build())
    val categoryPointsList: MutableStateFlow<List<CategoryQuantitySum>> = MutableStateFlow(listOf())

    val rankLabel: Int = R.string.admin_rank_chart_label
    val chartRankProducerState = MutableStateFlow(CartesianChartModelProducer.build())

    val rankWinnersItemList: StateFlow<List<WinnerItem>> =
        repository.getTop3Accounts()
            .map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )


    val pieState = MutableStateFlow(PieState())

    val accountOrderByPoints =
        repository.getAccountsOrderByPoints()
            .map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )


    init {
        setRandomTip()
        //if (!greetingAnimationHasPlayedOnce) {

        viewModelScope.launch {
            delay(300)
            getGreetingTextFromTime()
        }
        viewModelScope.launch {
            delay(2300)
            state.update {
                it.copy(greetingText = R.string.app_name)
            }
            //greetingAnimationHasPlayedOnce = true
        }
        //}
        cityLevelInit()
        initializeRankChart()
        initializeQuantityChart()
        initializePieChart()
    }

    private fun initializeQuantityChart() = viewModelScope.launch {
        repository.getSumQuantityPerCategory().collect { items ->
            if (items.isNotEmpty()) withContext(Dispatchers.Default) {
                categoryPointsList.update { items }
                chartProducerState.value.tryRunTransaction {
                    columnSeries {
                        series(items.map { it.totalQuantity })
                    }
                }
            }
        }
    }

    private fun cityLevelInit() = viewModelScope.launch {
        levelState.collect { city ->
            animatedCityLevel.update { city.percentInLevel }
            animatedCityLevelInt.update { city.pointsInLevel }
        }
    }

    private fun initializeRankChart() = viewModelScope.launch {
        rankWinnersItemList.collect { items ->
            if (items.isNotEmpty()) {
                val series = items.map { it.totalPoints }.toMutableList()
                if (series.size >= 2) {
                    val temp = series[0]
                    series[0] = series[1]
                    series[1] = temp
                }

                withContext(Dispatchers.Default) {
                    chartRankProducerState.value.tryRunTransaction {
                        columnSeries {
                            series(series)
                        }
                    }
                }
            }
        }
    }

    private fun initializePieChart() = viewModelScope.launch {
        repository.getSumQuantityPerMaterialInCategory(pieState.value.selectedCategory)
            .collect { listMaterialQuantitySum ->
                val totalQuantity = listMaterialQuantitySum.sumOf { it.totalQuantity }

                val percentOfMaterials = listMaterialQuantitySum.map {
                    Pair(it.name, it.totalQuantity.toFloat() / totalQuantity)
                }

                pieState.update {
                    it.copy(percentOfMaterials = percentOfMaterials)
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

        val greetingText = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 6..11 -> R.string.admin_good_morning
            else -> R.string.admin_good_afternoon
        }
        state.update {
            it.copy(greetingText = greetingText)
        }
    }

    fun onCategorySelectedPieChart(category: RecyclingCategory) = viewModelScope.launch {
        repository.getSumQuantityPerMaterialInCategory(category)
            .collect { listMaterialQuantitySum ->
                val totalQuantity = listMaterialQuantitySum.sumOf { it.totalQuantity }

                val percentOfMaterials = listMaterialQuantitySum.map {
                    Pair(it.name, it.totalQuantity.toFloat() / totalQuantity)
                }

                pieState.update {
                    it.copy(
                        selectedCategory = category,
                        percentOfMaterials = percentOfMaterials
                    )
                }
                setPieChartDialog(false)
            }
    }


    fun setPieChartDialog(value: Boolean) {
        pieState.update {
            it.copy(
                dialogOpened = value
            )
        }
    }
}

//var greetingAnimationHasPlayedOnce: Boolean = hide for now

data class AdminHomeState(
//    val greetingText: Int = if (greetingAnimationHasPlayedOnce) R.string.app_name else R.string.empty,
    val greetingText: Int = R.string.empty,
)

data class PieState(
    val selectedCategory: RecyclingCategory = RecyclingCategory.PLASTIC,
    val dialogOpened: Boolean = false,
    val recyclingCategories: EnumEntries<RecyclingCategory> = RecyclingCategory.entries,
    val percentOfMaterials: List<Pair<String, Float>> = listOf()
)