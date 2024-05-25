package com.example.greenifyme.ui.admin.rank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AdminRankModel(repository: GreenRepository) : ViewModel() {

    val state = MutableStateFlow(AdminRankState)

    val databaseItems =
        repository.getAccountsOrderByPoints()
            .map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )

}


data object AdminRankState {
    val label: Int = R.string.admin_rank_app_bar
}