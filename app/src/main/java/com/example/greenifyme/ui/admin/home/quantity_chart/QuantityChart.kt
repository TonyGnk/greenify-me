package com.example.greenifyme.ui.admin.home.quantity_chart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.model.AdminHomeModel
import com.example.greenifyme.ui.admin.home.model.AdminHomeState

@Composable
fun QuantityChart(
    model: AdminHomeModel = AdminHomeModel(),
    state: AdminHomeState = AdminHomeState(),
    horizontalPadding: Dp = 12.dp
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(12),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = horizontalPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(9.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Συνολική Ποσότητα",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W600,
                )
                MultiChoiceSegmented()
            }
            QuantityChartArea()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiChoiceSegmented() {
    val checkedList = remember { mutableIntStateOf(1) }
    val options = listOf("Τύποι", "Υλικά")
    MultiChoiceSegmentedButtonRow(
        modifier = Modifier
            .height(29.dp)
            .width(140.dp),
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size,
                ),

                icon = {
                    if (index == checkedList.intValue) {
                        SegmentedButtonDefaults.Icon(
                            active = index == checkedList.intValue
                        )
                    }
                },
                onCheckedChange = {
                    if (index != checkedList.intValue) {
                        checkedList.intValue = index
                    }
                },
                checked = index == checkedList.intValue
            ) {
                Text(label, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
fun QuantityChartArea() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = RoundedCornerShape(12),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Σύντομα Διαθέσιμο")
        }
    }
}

@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        QuantityChart()
    }
}