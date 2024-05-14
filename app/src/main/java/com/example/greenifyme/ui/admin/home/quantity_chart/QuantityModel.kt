package com.example.greenifyme.ui.admin.home.quantity_chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.GreenRepository
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuantityModel(repository: GreenRepository) : ViewModel() {

    val modelProducer = MutableStateFlow(CartesianChartModelProducer.build())

    private val listOfQuantities = repository.getQuantityForAllMaterials()
        .map { it }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )

    val listOfMaterialNames = repository.getMaterialNames()
        .map { it }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )


    init {
        viewModelScope.launch {
            listOfQuantities.collect { items ->
                if (items.isNotEmpty()) {
                    withContext(Dispatchers.Default) {
                        modelProducer.value.tryRunTransaction {
                            columnSeries {
                                series(
                                    items
                                )
                            }
                        }

                    }
                }
            }
        }
    }


}