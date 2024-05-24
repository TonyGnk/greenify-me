package com.example.greenifyme.ui.database_manager.content_shared.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentViewModel

@Composable
fun SearchButtons(model : ContentViewModel, state : ContentUiState) {
	Row(
		horizontalArrangement = Arrangement.SpaceAround,
		modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
	) {
		TextButton(onClick = { model.deleteAll(true) }) {
			Icon(
				imageVector = Icons.Outlined.Refresh,
				contentDescription = stringResource(state.strings.reset)
			)
			Spacer(modifier = Modifier.width(4.dp))
			Text(stringResource(state.strings.reset))
		}
		TextButton(onClick = { model.deleteAll() }) {
			Icon(
				imageVector = Icons.Outlined.Delete,
				contentDescription = stringResource(state.strings.deleteAll)
			)
			Spacer(modifier = Modifier.width(4.dp))
			Text(stringResource(state.strings.deleteAll))
		}
	}
}