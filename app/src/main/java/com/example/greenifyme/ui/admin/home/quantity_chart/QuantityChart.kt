package com.example.greenifyme.ui.admin.home.quantity_chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.admin.home.model.AdminHomeModel
import com.example.greenifyme.ui.admin.home.model.AdminHomeState
import com.example.greenifyme.ui.admin.home.shared.DefaultCard

@Composable
fun QuantityChart(
    model2: AdminHomeModel = viewModel(factory = ViewModelProvider.Factory),
    state: AdminHomeState = AdminHomeState(),
    horizontalPadding: Dp = 12.dp
) {
    val model: QuantityModel = viewModel(factory = ViewModelProvider.Factory)
    DefaultCard() {
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
            QuantityChartArea(model, state)
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

@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        QuantityChart()
    }
}