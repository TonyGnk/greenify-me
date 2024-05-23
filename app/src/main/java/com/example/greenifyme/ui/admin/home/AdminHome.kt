package com.example.greenifyme.ui.admin.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.admin.home.app_bar.AdminHomeAppBar
import com.example.greenifyme.ui.admin.home.levelOfCity.LevelOfCity
import com.example.greenifyme.ui.admin.home.model.AdminHomeModel
import com.example.greenifyme.ui.admin.home.quantity_chart.QuantityChart
import com.example.greenifyme.ui.admin.home.tip_of_day.TipOfDay

@Composable
fun AdminHome() {
    val model: AdminHomeModel = viewModel(factory = ViewModelProvider.Factory)
    val state by model.state.collectAsState()
    val tipState by model.tipState.collectAsState()
    val horizontalPadding = 16.dp

    //We create a surface to hold our content
    Surface(
        //Here we specify attributes for the surface
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
        modifier = Modifier.fillMaxSize() //We want the surface to fill the entire screen
        // If we don't add fillMaxSize, the surface will only take the space it needs
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 2.dp)
        ) {
            item {
                AdminHomeAppBar(model, state, horizontalPadding)
            }
            item {
                TipOfDay(tipState, horizontalPadding)
            }
            item {
                LevelOfCity(model, horizontalPadding)
            }
            item {
                QuantityChart(model, state, horizontalPadding)
            }
        }
    }
}


@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        AdminHome()
    }
}
