package com.example.greenifyme.ui.database.manager.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector


/**
 * This composable displays a search bar with a close button.
 *
 * @param query The current query in the search bar.
 * @param onQueryChange Callback for when the query changes.
 * @param onCloseSearch Callback for when the search is closed.
 * @param isSearching Boolean indicating whether search is active.
 * @param searchPlaceHolder Placeholder text for the search bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerContentSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onCloseSearch: () -> Unit,
    isSearching: Boolean,
    searchPlaceHolder: String,
) {
    DockedSearchBar(
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        ),
        tonalElevation = 0.dp,
        query = query,
        onQueryChange = { onQueryChange(it) },
        onSearch = {},
        active = false,
        onActiveChange = {},
        placeholder = { Text(searchPlaceHolder) },
        leadingIcon = {
            Icon(
                painter = getVector(drawableValue = R.drawable.search),
                contentDescription = searchPlaceHolder,
                modifier = Modifier.size(19.dp),
            )
        },
        trailingIcon = {
            if (isSearching) {
                IconButton(onCloseSearch) {
                    Icon(
                        painter = getVector(drawableValue = R.drawable.cross),
                        contentDescription = getString(R.string.manager_content_close_search),
                        modifier = Modifier.size(17.dp),
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = getDimen(R.dimen.horizontalScreenPadding))
    ) {}
}