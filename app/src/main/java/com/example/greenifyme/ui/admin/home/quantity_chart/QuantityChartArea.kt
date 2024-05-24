package com.example.greenifyme.ui.admin.home.quantity_chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.admin.home.model.AdminHomeState
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.common.shape.Shape

@Composable
fun QuantityChartArea(model: QuantityModel, state: AdminHomeState) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(12),
        modifier = Modifier.fillMaxSize()
    ) { ComposeChart6(model) }
}

@Composable
fun RowOfDatabaseItems(model: QuantityModel) {
    val listItems by model.listOfMaterialNames.collectAsState()
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(items = listItems) { item ->
            Text(text = item)
        }
    }
}

@Composable
private fun ComposeChart6(model: QuantityModel) {
    val newModelProducer by model.modelProducer.collectAsState()
    val listItems by model.listOfMaterialNames.collectAsState()
    val bottomAxisValueFormatter2 =
        if (listItems.isNotEmpty()) {
            CartesianValueFormatter { x, _, _ -> listItems[x.toInt()] }
        } else {
            CartesianValueFormatter { x, _, _ -> x.toString() }
        }
    val shape = remember {
        Shape.rounded(topLeftDp = 12f, topRightDp = 12f)
    }
    CartesianChartHost(
        chart =
        rememberCartesianChart(
            rememberColumnCartesianLayer(
                ColumnCartesianLayer.ColumnProvider.series(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                    ).map {
                        rememberLineComponent(
                            color = it,
                            thickness = 44.dp,
                            shape = shape
                        )
                    },
                ),
            ),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(
                valueFormatter = bottomAxisValueFormatter2
            ),
        ),
        modelProducer = newModelProducer,
        marker = rememberMarker(),
        runInitialAnimation = true,
        zoomState = rememberVicoZoomState(zoomEnabled = true),
    )
}