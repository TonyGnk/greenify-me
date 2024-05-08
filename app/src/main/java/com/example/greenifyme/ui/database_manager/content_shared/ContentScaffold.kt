package com.example.greenifyme.ui.database_manager.content_shared

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.ui.database_manager.LargeTextAtCenter
import com.example.greenifyme.ui.database_manager.content_shared.bottom_sheet.ContentBottomSheet
import com.example.greenifyme.ui.database_manager.content_shared.list_view.ContentListView
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentViewModel
import com.example.greenifyme.ui.database_manager.content_shared.search.ContentSearchArea

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContentScaffold(
    model: ContentViewModel,
    listItems: List<DataObject>,
    state: ContentUiState,
) {
    Scaffold(
        containerColor = Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(
                content = {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = stringResource(state.strings.fabText)
                    )
                },
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = { model.openSheetAndSwitchToCreateMode() },
            )
        },
    ) {
        if (state.showBottomSheet) {
            ContentBottomSheet(model, state)
        }
        Column {
            ContentSearchArea(model, state)
            Spacer(Modifier.height(12.dp))
            if (listItems.isEmpty()) LargeTextAtCenter(state.strings.emptyDatabase)
            else ContentListView(model, listItems)
        }
    }
}


