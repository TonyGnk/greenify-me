package com.example.greenifyme.ui.database_manager.account.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountModel
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSearchArea(
    uiState: AccountUiState,
    model: AccountModel,
) {
    Column(
        modifier = Modifier
            .clip(SearchBarDefaults.dockedShape)
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        AccountSearch(model, uiState)
        AccountRowOfButtons(model)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSearch(
    model: AccountModel,
    accountUiState: AccountUiState,
) {
    DockedSearchBar(
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        ),
        tonalElevation = 0.dp,
        query = accountUiState.searchQuery,
        onQueryChange = { model.onQueryChange(it) },
        onSearch = {},
        active = accountUiState.isSearching,
        onActiveChange = { model.onSearch() },
        placeholder = { Text("Search Accounts") },
        leadingIcon = {
            if (accountUiState.isSearching) {
                IconButton(onClick = { model.onSearch(true) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Close",
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            }
        },
        trailingIcon = {
            if (accountUiState.isSearching) {
                IconButton(onClick = { model.onSearch() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        AccountList(
            model = model,
            accountItems = model.getSearchItems(),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun AccountRowOfButtons(model: AccountModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
    ) {
        TextButton(onClick = { model.deleteAll(true) }) {
            Text(stringResource(id = R.string.reset_database))
        }
        TextButton(onClick = { model.deleteAll() }) {
            Text(stringResource(id = R.string.delete_all))
        }
    }
}