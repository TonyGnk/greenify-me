package com.example.greenifyme.ui.admin.home.levelOfCity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.admin.home.model.AdminHomeModel
import com.example.greenifyme.ui.shared.SharedCard

@Composable
@Preview
fun LevelOfCity(
    model: AdminHomeModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val state by model.cityLevelState.collectAsState()

    SharedCard(height = 140.dp) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = state.image),
                contentDescription = "Building with a clock",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between elements

            )
            {
                Text(
                    text = stringResource(id = state.levelName),
                    style = MaterialTheme.typography.labelLarge

                )
                Text(
                    text = "Points:${state.points}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black
                )

            }

        }
    }

}


