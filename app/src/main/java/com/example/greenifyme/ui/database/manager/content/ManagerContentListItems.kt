package com.example.greenifyme.ui.database.manager.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getFullTimeFromEpoch
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Track

/**
 * This composable function displays the header row for different types of DataObject items.
 *
 * @param item DataObject instance that represents different types of items (Account, Form, Track, Material)
 */
@Composable
fun ManagerContentHeaderItem(item: DataObject) {
    RowLayout {
        Text(
            text = when (item) {
                is Account -> getString(R.string.manager_content_header_account)
                is Form -> getString(R.string.manager_content_header_form)
                is Track -> getString(R.string.manager_content_header_track)
                is Material -> getString(R.string.manager_content_header_material)
            },
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}


/**
 * This composable function displays a row with details of a DataObject item and a delete button.
 *
 * @param item DataObject instance that represents different types of items (Account, Form, Track, Material)
 * @param onDeleteClick Lambda function to handle the delete button click event
 */
@Composable
fun ManagerContentDetailItem(
    item: DataObject,
    onDeleteClick: (DataObject) -> Unit,
) {
    RowLayout {
        Text(
            text = when (item) {
                is Account -> "${item.accountId}    ${item.name} | ${item.email}"
                is Form -> "${item.formId}    ${item.accountId} | ${getFullTimeFromEpoch(item.createdAt)}"
                is Track -> "${item.trackId}    ${item.formId} | ${item.materialId} | ${item.quantity}"
                is Material -> "${item.materialId}    ${item.name} | ${getString(item.category.description)} | ${item.type.pointsPerGram} | ${item.type.pointsPerPiece}"
            },
            style = MaterialTheme.typography.bodyMedium,
        )
        IconButton(
            onClick = { onDeleteClick(item) },
        ) {
            Icon(
                painter = getVector(R.drawable.trash),
                contentDescription = getString(R.string.delete),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

/**
 * This composable function creates a row layout with customizable content.
 *
 * @param content Lambda function to provide the content to be displayed in the row
 */
@Composable
private fun RowLayout(
    content: @Composable () -> Unit
) {
    ListItem(
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        headlineContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                content()
            }
        },
    )
}