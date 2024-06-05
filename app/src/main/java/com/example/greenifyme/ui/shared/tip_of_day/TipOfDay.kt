package com.example.greenifyme.ui.shared.tip_of_day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getString


/**
 * This composable function displays the "Tip of the Day" card with a title and a tip.
 * The card is styled with primary color and the texts are centrally aligned.
 *
 * @param state The state object containing the label and the selected tip. Default is a new instance of TipState.
 */
@Composable
@Preview
fun TipOfDay(
    state: TipState = TipState(selectedTip = R.string.recycling_tip_10)
) {

    val brush: Brush = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.tertiary
        )
    )

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(
                brush = brush
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = getString(state.label),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.W800,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = getString(state.selectedTip),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}