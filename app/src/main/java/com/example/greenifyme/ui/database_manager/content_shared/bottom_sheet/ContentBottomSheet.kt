package com.example.greenifyme.ui.database_manager.content_shared.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.database_manager.account.AccountSheetContent
import com.example.greenifyme.ui.database_manager.content_shared.model.AccountState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentViewModel
import com.example.greenifyme.ui.database_manager.content_shared.model.FormState
import com.example.greenifyme.ui.database_manager.content_shared.model.MaterialState
import com.example.greenifyme.ui.database_manager.content_shared.model.TrackState
import com.example.greenifyme.ui.database_manager.material.MaterialSheetContent
import com.example.greenifyme.ui.database_manager.record.RecordSheetContent
import com.example.greenifyme.ui.database_manager.track.TrackSheetContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentBottomSheet(
    model: ContentViewModel,
    state: ContentUiState
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = { model.setBottomSheet(false) },
        windowInsets = BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Bottom),
        sheetState = sheetState,
        dragHandle = { },
    ) {
        val onItemSave: () -> Unit = {
            coroutineScope.launch {
                if (state.openSheetForEditing) model.updateItem()
                else model.saveItem()
                sheetState.hide()
                model.setBottomSheet(false)
            }
        }
        val modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(12.dp)
        when (state.itemState) {
            is AccountState -> AccountSheetContent(
                state = state,
                updateUiState = { model.updateFields(it) },
                onItemSave = onItemSave,
                accountState = state.itemState,
                modifier = modifier
            )

            is FormState -> RecordSheetContent(
                state = state,
                updateUiState = { model.updateFields(it) },
                onItemSave = onItemSave,
                formState = state.itemState,
                modifier = modifier
            )

            is TrackState -> TrackSheetContent(
                state = state,
                updateUiState = { model.updateFields(it) },
                onItemSave = onItemSave,
                accountState = state.itemState,
                modifier = modifier
            )

            is MaterialState -> MaterialSheetContent(
                state = state,
                updateUiState = { model.updateFields(it) },
                onItemSave = onItemSave,
                materialState = state.itemState,
                modifier = modifier
            )

        }
    }
}


