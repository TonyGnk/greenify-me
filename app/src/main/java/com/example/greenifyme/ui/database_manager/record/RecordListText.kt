package com.example.greenifyme.ui.database_manager.record

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.greenifyme.data.Form
import com.example.greenifyme.ui.database_manager.content_shared.model.getFullTimeFromEpoch

@Composable
fun RecordListText(
    form: Form,
    modifier: Modifier
) {
    Text(
        text = form.formId.toString() + "     "
                + form.accountId + " | " + getFullTimeFromEpoch(
            form.createdAt
        ),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = if (!form.hasAdminViewed) FontWeight.W900 else FontWeight.W300,
        modifier = modifier
    )
}