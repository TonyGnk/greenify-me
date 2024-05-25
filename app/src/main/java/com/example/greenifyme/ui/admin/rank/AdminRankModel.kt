package com.example.greenifyme.ui.admin.rank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AdminRankModel(repository: GreenRepository) : ViewModel() {

    val label: Int = R.string.admin_rank_app_bar

    val databaseItems =
        repository.getAccountsOrderByPoints()
            .map { it }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )
}