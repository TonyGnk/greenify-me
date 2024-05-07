package com.example.greenifyme.ui.database_manager.content_shared.list_view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentViewModel

@Composable
fun ContentListView(
    model: ContentViewModel,
    listItems: List<DataObject>,
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 78.dp)
    ) {
        items(items = listItems) { item ->
            ContentListItem(
                model = model,
                item = item,
            )
        }
    }
}