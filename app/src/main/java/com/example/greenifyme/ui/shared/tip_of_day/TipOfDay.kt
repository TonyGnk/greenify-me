package com.example.greenifyme.ui.shared.tip_of_day

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.ui.shared.SharedCard


/**
 * This composable function displays the "Tip of the Day" card with a title and a tip.
 * The card is styled with primary color and the texts are centrally aligned.
 *
 * @param state The state object containing the label and the selected tip. Default is a new instance of TipState.
 */
@Composable
@Preview
fun TipOfDay(state: TipState = TipState()) {
    SharedCard(color = MaterialTheme.colorScheme.primary) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(14.dp)
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