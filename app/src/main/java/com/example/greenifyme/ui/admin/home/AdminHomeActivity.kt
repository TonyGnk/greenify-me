package com.example.greenifyme.ui.admin.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.admin.home.charts.QuantityChart
import com.example.greenifyme.ui.admin.home.charts.RankChartMain
import com.example.greenifyme.ui.shared.SharedLazyColumn
import com.example.greenifyme.ui.shared.tip_of_day.TipOfDay


class AdminHomeActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                AdminHome()
            }
        }
    }
}

/**
 * This composable function displays the Admin Home screen with an app bar, a tip of the day,
 * level of city information, and various charts. It collects states from AdminHomeModel.
 */
@Composable
@Preview
private fun AdminHome() {
    val model: AdminHomeModel = viewModel(factory = ViewModelProvider.Factory)
    val state by model.state.collectAsState()
    val tipState by model.tipState.collectAsState()

    SharedLazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            AdminHomeAppBar(getString(state.greetingText))
        }
        item {
            TipOfDay(tipState)
        }
        item {
            LevelOfCity()
        }
        item {
            QuantityChart()
        }
        item {
            RankChartMain()
        }
    }
}