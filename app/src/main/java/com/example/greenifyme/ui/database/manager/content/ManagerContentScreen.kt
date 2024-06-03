package com.example.greenifyme.ui.database.manager.content

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.data.DataObjectType
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Track
import com.example.greenifyme.ui.database.manager.navigation.LargeTextAtCenter
import com.example.greenifyme.ui.shared.SharedAnimatedList
import com.example.greenifyme.ui.shared.SharedColumn
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Displays the content screen based on the given type.
 *
 * @param model ViewModel managing the content.
 * @param objectType Type of data to display.
 * @param navigationBar Function to display navigation bar.
 */
@Composable
fun ManagerContentScreen(
    model: ManagerViewModel,
    objectType: DataObjectType,
    navigationBar: @Composable () -> Unit,
) {
    val context: Context = LocalContext.current
    val state by model.state.collectAsState()
    val visible = remember { MutableStateFlow(false) }
    val isComponentVisible by visible.collectAsState()
    LaunchedEffect(Unit) { visible.value = true }

    val dataList by remember(state) {
        derivedStateOf {
            when (objectType) {
                DataObjectType.ACCOUNT -> state.accounts
                DataObjectType.FORM -> state.forms
                DataObjectType.TRACK -> state.tracks
                DataObjectType.MATERIAL -> state.materials
            }
        }
    }

    SharedColumn(
        applyHorizontalPadding = false,
    ) {
        ManagerContentSearchBar(
            query = state.searchQuery,
            isSearching = state.isSearching,
            searchPlaceHolder = getString(R.string.manager_content_search),
            onCloseSearch = { model.closeSearchAndReload(objectType) },
            onQueryChange = { model.searchItems(it, objectType, context) }
        )
        ContentList(
            modifier = Modifier.weight(1f),
            dataList = dataList,
            isComponentVisible = isComponentVisible,
            onDeleteItem = { model.deleteItemFromDatabase(it) }
        )
        navigationBar()
    }
}

/**
 * Displays a list of data objects.
 *
 * @param modifier Modifier for the list layout.
 * @param dataList List of data objects to display.
 * @param isComponentVisible Boolean indicating whether the list is visible.
 * @param onDeleteItem Function to delete an item from the list.
 */
@Composable
private fun ContentList(
    modifier: Modifier,
    dataList: List<DataObject>,
    isComponentVisible: Boolean,
    onDeleteItem: (DataObject) -> Unit
) {
    Box(modifier) {
        SharedAnimatedList(isComponentVisible) {
            if (dataList.isNotEmpty()) {
                LazyColumn {
                    item {
                        ManagerContentHeaderItem(dataList.first())
                    }

                    items(
                        items = dataList, key = {
                            when (it) {
                                is Account -> it.accountId
                                is Form -> it.formId
                                is Material -> it.materialId
                                is Track -> it.trackId
                            }
                        }
                    ) { item ->
                        ManagerContentDetailItem(
                            item = item,
                            onDeleteClick = {
                                onDeleteItem(it)
                            },
                        )
                    }
                }
            } else {
                LargeTextAtCenter(
                    getString(R.string.database_manager_content_no_items_found)
                )
            }
        }
    }
}