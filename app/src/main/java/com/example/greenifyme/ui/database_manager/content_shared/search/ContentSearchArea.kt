package com.example.greenifyme.ui.database_manager.content_shared.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.database_manager.content_shared.list_view.ContentListView
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentUiState
import com.example.greenifyme.ui.database_manager.content_shared.model.ContentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentSearchArea(
    model: ContentViewModel,
    state: ContentUiState,
) {
    Column(
        modifier = Modifier
            .clip(SearchBarDefaults.dockedShape)
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        ContentSearch(model, state)
        SearchButtons(model, state)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentSearch(
    model: ContentViewModel,
    state: ContentUiState,
) {
    DockedSearchBar(
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        ),
        tonalElevation = 0.dp,
        query = state.searchQuery,
        onQueryChange = { model.searchOnQueryChange(it) },
        onSearch = { model.searchOnSearchButton() },
        active = state.isSearching,
        onActiveChange = { model.searchOnSearch() },
        placeholder = { Text(stringResource(state.strings.searchPlaceHolder)) },
        leadingIcon = {
            if (state.isSearching) {
                IconButton(onClick = { model.searchOnSearch(true) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(state.strings.closeSearch),
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(state.strings.searchPlaceHolder),
                )
            }
        },
        trailingIcon = {
            if (state.isSearching) {
                IconButton(onClick = { model.searchOnSearch() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(state.strings.closeSearch),
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        if (model.searchGetResults().isNotEmpty()) {
            ContentListView(
                model = model,
                listItems = model.searchGetResults(),
            )
        }
    }
}

