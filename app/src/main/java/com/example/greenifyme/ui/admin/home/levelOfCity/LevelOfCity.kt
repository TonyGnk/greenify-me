package com.example.greenifyme.ui.admin.home.levelOfCity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.admin.home.model.AdminHomeModel
import com.example.greenifyme.ui.admin.home.model.CityLevel1
import com.example.greenifyme.ui.admin.home.model.CityLevels
import com.example.greenifyme.ui.admin.home.shared.DefaultCard

@Composable
fun LevelOfCity(
    model: AdminHomeModel = viewModel(factory = ViewModelProvider.Factory),
    state: CityLevels = CityLevel1(23100),
    horizontalPadding: Dp = 12.dp
) {

    //Use the DefaultCard composable
    DefaultCard(horizontalPadding, 140.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(state.image),
                contentDescription = "Building with a clock",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }
    }
}


@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        LevelOfCity()
    }
}