package com.example.greenifyme.ui.admin.home.charts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.relations.WinnerItem
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RankChartModel(repository: GreenRepository) : ViewModel() {

    val label: Int = R.string.admin_rank_chart_label
    val score: Int = R.string.admin_rank_chart_button
    val chartProducerState = MutableStateFlow(CartesianChartModelProducer.build())

    val winnersItemList: StateFlow<List<WinnerItem>> =
        repository.getTop3Accounts()
            .map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )

    init {
        viewModelScope.launch {
            winnersItemList.collect { items ->
                if (items.isNotEmpty()) {
                    val series = items.map { it.totalPoints }.toMutableList()
                    if (series.size >= 2) {
                        val temp = series[0]
                        series[0] = series[1]
                        series[1] = temp
                    }

                    withContext(Dispatchers.Default) {
                        chartProducerState.value.tryRunTransaction {
                            columnSeries {
                                series(series)
                            }
                        }
                    }
                }
            }
        }
    }
}