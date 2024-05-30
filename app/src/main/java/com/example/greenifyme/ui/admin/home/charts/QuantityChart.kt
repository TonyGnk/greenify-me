package com.example.greenifyme.ui.admin.home.charts

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.ViewModelProvider
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.ui.shared.SharedAppBarType
import com.example.greenifyme.ui.shared.SharedCard
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

/**
 * This composable function displays a quantity chart within a card, including a top bar with a label and the chart area.
 * It retrieves the QuantityChartModel using a view model factory.
 */
@Composable
@Preview
fun QuantityChart() {
    val model: QuantityChartModel = viewModel(factory = ViewModelProvider.Factory)
    SharedCard(
        topBarType = SharedAppBarType.Enable(getString(model.label)),
    ) {
        //SharedChartTopBar()
        SharedChartCard { Chart(model) }
    }
}

/**
 * This composable function displays the area of a quantity chart using the provided chart model.
 * It sets up the chart with custom axes, colors, and zoom functionality.
 *
 * @param model The QuantityChartModel containing the data and state for the chart.
 */
@Composable
private fun Chart(model: QuantityChartModel) {
    val chartProducerState by model.chartProducerState.collectAsState()
    val categoryPointsList by model.categoryPointsList.collectAsState()
    val categories = categoryPointsList.map { getString(stringValue = it.category.description) }

    // Formatter for the bottom axis to display category descriptions
    val bottomAxisFormatter = CartesianValueFormatter { x, _, _ ->
        if (categories.isNotEmpty()) categories[x.toInt()]
        else x.toString()
    }

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberColumnCartesianLayer(
                ColumnCartesianLayer.ColumnProvider.series(
                    listOf(MaterialTheme.colorScheme.primary).map {
                        rememberLineComponent(
                            color = it,
                            thickness = 30.dp,
                            shape = Shape.rounded(topLeftDp = 16f, topRightDp = 16f)
                        )
                    },
                ),
            ),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(
                guideline = rememberLineComponent(Color.Transparent),
                valueFormatter = bottomAxisFormatter
            ),
        ),
        modelProducer = chartProducerState, // Provides the chart data
        marker = sharedMarker(), // Sets the marker style
        runInitialAnimation = true,
        zoomState = rememberVicoZoomState(zoomEnabled = true),
    )
}