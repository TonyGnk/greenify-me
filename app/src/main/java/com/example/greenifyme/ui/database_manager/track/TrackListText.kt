package com.example.greenifyme.ui.database_manager.track

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.greenifyme.data.Track

@Composable
fun TrackListText(
    track: Track,
    modifier: Modifier
) {
    Text(
        text = track.trackId.toString() + "     "
                + track.formId + " | " + track.materialId + " | " + track.quantity,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.W300,
        modifier = modifier
    )
}