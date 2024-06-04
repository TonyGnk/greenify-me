package com.example.greenifyme.ui.user.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.SharedModelProvider
import com.example.greenifyme.ui.shared.SharedAppBarType
import com.example.greenifyme.ui.shared.SharedCard
import com.example.greenifyme.ui.shared.SharedProgressBar

/**
 * This composable represents the Citizen Points section.
 *
 * @param model The view model to use for state management.
 */
@Composable
@Preview
fun CitizenPoints(model: UserHomeModel = viewModel(factory = SharedModelProvider.Factory(false))) {
    val state by model.pointState.collectAsState()
    val animatedState by model.animatedState.collectAsState()

    SharedCard(
        topBarType = SharedAppBarType.Enable("Citizen Points"),
        applyHorizontalPadding = false,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp))
            {
                Text(
                    text = state.points.toString(),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "${state.pointsInLevel}/${state.targetPointsInLevel} points",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            SharedProgressBar(
                percent = animatedState,
                startingLabel = (state.targetingLevel.order - 1).toString(),
                endingLabel = state.targetingLevel.order.toString(),
            )
        }
    }
}