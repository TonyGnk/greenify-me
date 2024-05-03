package com.example.greenifyme.ui.admin.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.AdminHomeViewModel
import com.example.greenifyme.ui.admin.home.quantity_chart.QuantityChart

@Composable
fun AdminHome() {
    //First let's bring the viewModel
    val model = AdminHomeModel() //Give this to our composable functions

    //We create a surface to hold our content
    Surface(
        //Here we specify attributes for the surface
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
        modifier = Modifier.fillMaxSize() //We want the surface to fill the entire screen
        // If we don't add fillMaxSize, the surface will only take the space it needs
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            //Add padding to the top of the screen according to each device
            modifier = Modifier.statusBarsPadding()
        ) {
            AdminHomeAppBar(model) //Send the viewModel
            //TipOfDay
            //LevelOfCity
            QuantityChart()
            //UpdateForm
            //Graph2
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