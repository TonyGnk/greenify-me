package com.example.greenifyme.ui.database_manager.content_shared.list_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Track
import com.example.greenifyme.ui.database_manager.account.AccountListText
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentViewModel
import com.example.greenifyme.ui.database_manager.material.MaterialListText
import com.example.greenifyme.ui.database_manager.record.RecordListText
import com.example.greenifyme.ui.database_manager.track.TrackListText

@Composable
fun ContentListItem(
    model: ContentViewModel,
    item: DataObject,
) {
    ListItem(
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        modifier = Modifier
            .clickable(onClick = { model.onListItemClick(item) })
            .fillMaxWidth()
            .height(40.dp),
        headlineContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                when (item) {
                    is Account -> AccountListText(
                        account = item,
                        Modifier.weight(1f)
                    )

                    is Form -> RecordListText(
                        form = item,
                        modifier = Modifier.weight(1f)
                    )

                    is Track -> TrackListText(
                        track = item,
                        Modifier.weight(1f)
                    )

                    is Material -> MaterialListText(
                        material = item,
                        Modifier.weight(1f)
                    )

                }

                IconButton(
                    onClick = { model.deleteItem(item) },
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription =
                        stringResource(id = R.string.delete)
                    )
                }
            }

        },
    )
}