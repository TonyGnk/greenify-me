package com.example.greenifyme.ui.admin.home.charts


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.compose_utilities.theme.getThemeColorVariants
import com.example.greenifyme.ui.shared.SharedAppBarType
import com.example.greenifyme.ui.shared.SharedCard
import com.example.greenifyme.ui.shared.SharedChartCard


@Composable
@Preview
fun PieChart(
    listOfPiecesWithNames: List<Pair<String, Float>> = listOf(
        Pair("A", 0.2f), Pair("B", 0.3f), Pair("C", 0.5f)
    ),
    label: String = "Glass Category",
    onSelectButtonClicked: () -> Unit = {}
) {
    SharedCard(
        topBarType = SharedAppBarType.Enable(label),
        height = 215.dp,
        rightAppBarItem = {
            TextButton(
                contentPadding = PaddingValues(vertical = 0.dp, horizontal = 8.dp),
                onClick = onSelectButtonClicked,
            ) {
                Text(
                    text = "Select Category",
                )

            }
        }
    ) {
        SharedChartCard { Chart(listOfPiecesWithNames) }
    }
}


@Composable
private fun Chart(
    givenListOfPiecesWithNames: List<Pair<String, Float>> = listOf(
        Pair("A", 0.1f),
        Pair("A", 0.1f),
        Pair("B", 0.3f),
        Pair("C", 0.35f),
        Pair("D", 0.1f),
        Pair("E", 0.01f),
        Pair("F", 0.04f)
    )
) {
    val listOfPiecesWithNames: List<Pair<String, Float>> =
        givenListOfPiecesWithNames.ifEmpty {
            listOf(
                Pair("No items", 1f)
            )
        }

    val listOnlyOfNames: List<String> = listOfPiecesWithNames.map { it.first }
    val listOfPieces: List<Float> = listOfPiecesWithNames.map { it.second }

    val colors = getThemeColorVariants(listOnlyOfNames.size)

    val positionList: MutableList<Triple<Float, Float, Color>> = mutableListOf()
    listOfPieces.forEachIndexed { index, list ->

        val lowest = if (index == 0) 0f else positionList[index - 1].second + 0.001f

        val highest = lowest + list - 0.001f

        positionList.add(Triple(lowest, highest, colors[index]))
    }

    val newList: List<Pair<Float, Color>> = positionList.flatMap { (x, y, color) ->
        listOf(Pair(x, color), Pair(y, color))
    }

    val gradient = Brush.sweepGradient(
        colorStops = newList.toTypedArray(),
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    ) {
        LazyColumn {
            items(listOfPieces.size) { index ->
                val (name, percent) = listOfPiecesWithNames[index]
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(positionList[index].third)
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = "$name: ${(percent * 100).toInt()}%",
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
                .background(gradient, CircleShape)
        )
    }
}


@Composable
@Preview
private fun Preview() {
    ComposeTheme { Chart() }
}