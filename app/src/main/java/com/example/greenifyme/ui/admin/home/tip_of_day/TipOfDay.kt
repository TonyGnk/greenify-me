package com.example.greenifyme.ui.admin.home.tip_of_day

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
import com.example.greenifyme.ui.admin.home.shared.DefaultCard

/**
 * This is the TipOfDay Composable function. It displays a card with a daily tip for admin and user.
 *
 * @param state The current state of the tip, which includes the selected tip from resources.
 */
@Composable
@Preview
fun TipOfDay(state: TipState = TipState()) {
    DefaultCard(color = MaterialTheme.colorScheme.primary) {
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