package com.example.greenifyme.ui.admin.home.charts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.relations.CategoryQuantitySum
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


class QuantityChartModel(repository: GreenRepository) : ViewModel() {

    val label: Int = R.string.admin_quantity_chart_label
    val chartProducerState = MutableStateFlow(CartesianChartModelProducer.build())

    val categoryPointsList: StateFlow<List<CategoryQuantitySum>> =
        repository.getSumQuantityPerCategory()
            .map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )

    init {
        viewModelScope.launch {
            categoryPointsList.collect { items ->
                if (items.isNotEmpty()) withContext(Dispatchers.Default) {
                    chartProducerState.value.tryRunTransaction {
                        columnSeries {
                            series(items.map { it.totalQuantity })
                        }
                    }
                }
            }
        }
    }
}