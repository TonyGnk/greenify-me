package com.example.greenifyme.ui.admin.home.charts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getVector
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer.ColumnProvider.Companion.series
import com.patrykandpatrick.vico.core.common.shape.Shape

/**
 * Displays the area of the rank chart, showcasing the top 3 winners' names and points.
 *
 * @param model: ViewModel containing chart data.
 */
@Composable
fun RankChartArea(model: RankChartModel) {
    val producer by model.chartProducerState.collectAsState()
    val winnersItemList by model.winnersItemList.collectAsState()
    val names = winnersItemList.map { it.name }.toMutableList()
    val points = winnersItemList.map { it.totalPoints }.toMutableList()

    if (winnersItemList.size == 3) {
        // We want the highest to be at the center. So we swap the first two elements
        names[0] = names[1].also { names[1] = names[0] }
        points[0] = points[1].also { points[1] = points[0] }

        SharedChartCard {
            Box(Modifier.padding(bottom = 8.dp)) {
                //We put one on top of the other like a stack
                Chart(producer)
                LabelsLayer(names, points)
            }
        }
    }
}

/**
 * Displays labels for the chart bars, containing names and points of winners.
 *
 * @param names: Mutable list of winner names.
 * @param points: Mutable list of winner points.
 */
@Composable
fun LabelsLayer(
    names: MutableList<String>, points: MutableList<Int>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        VerticalSpacer(Modifier.weight(1f))
        Bar(Modifier.weight(5f)) {
            Labels(names, points, 0)
        }
        VerticalSpacer(Modifier.weight(2f))
        Bar(Modifier.weight(5f)) {
            Labels(names, points, 1)
        }
        VerticalSpacer(Modifier.weight(2f))
        Bar(Modifier.weight(5f)) {
            Labels(names, points, 2)
        }
        VerticalSpacer(Modifier.weight(1f))
    }
}

@Composable
private fun VerticalSpacer(modifier: Modifier = Modifier) =
    Spacer(modifier.fillMaxHeight())

@Composable
private fun Bar(modifier: Modifier = Modifier, content: @Composable () -> Unit) =
    Box(modifier.fillMaxHeight()) { content() }


/**
 * Displays labels containing names and points for each winner.
 *
 * @param names: Mutable list of winner names.
 * @param points: Mutable list of winner points.
 * @param index: Index of the current winner.
 */
@Composable
private fun Labels(
    names: MutableList<String>, points: MutableList<Int>, index: Int
) {
    val percentFirstToCurrent =
        (points[index].toFloat() / (points[1].toFloat()) * 100)
    val restSize = 100 - percentFirstToCurrent
    Column {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(restSize + 70f)
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 6.dp)
        ) {
            //Is the second (centered)
            if (index == 1) {
                Icon(
                    painter = getVector(drawableValue = R.drawable.trophy_star),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = names[index],
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                lineHeight = TextUnit(15f, TextUnitType.Sp),
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(percentFirstToCurrent)
                .fillMaxWidth()
        ) {
            val rankIndex = when (index) {
                1 -> 1
                2 -> 3
                else -> 2
            }
            val percentCurrentToLargest = (points[index].toFloat() / (points[0].toFloat()) * 100)

            if (percentCurrentToLargest < 40) {
                RankingTextSmall(rankIndex.toString())
            } else
                RankingText(rankIndex.toString())
        }
    }
}

@Composable
private fun RankingText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.W900,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
private fun RankingTextSmall(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.W900,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

/**
 * Displays the chart using a Cartesian coordinate system.
 *
 * @param producer: Producer for the Cartesian chart model.
 */
@Composable
private fun Chart(producer: CartesianChartModelProducer) {
    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberColumnCartesianLayer(
                series(
                    listOf(MaterialTheme.colorScheme.primary).map {
                        rememberLineComponent(
                            color = it,
                            thickness = 68.dp,
                            shape = Shape.rounded(14f)
                        )
                    },
                ),
            ),
        ),
        modelProducer = producer,
        marker = sharedMarker(140f, false),
        runInitialAnimation = true,
        zoomState = rememberVicoZoomState(zoomEnabled = false),
    )
}

