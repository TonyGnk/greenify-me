package com.example.greenifyme.ui.database_manager.record

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.greenifyme.data.Record
import com.example.greenifyme.ui.database_manager.content_shared.model.findTimeFromEpoch

@Composable
fun RecordListText(
    record: Record,
    modifier: Modifier
) {
    Text(
        text = record.recordId.toString() + "     "
                + record.accountId + " | " + findTimeFromEpoch(
            record.createdAt
        ),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = if (!record.hasAdminViewed) FontWeight.W900 else FontWeight.W300,
        modifier = modifier
    )
}