package com.example.greenifyme.ui.shared

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R

/**
 * This composable creates a shared column layout with scaffold padding and optional horizontal padding.
 *
 * @param modifier Modifier for the Column composable.
 * @param verticalArrangement Vertical arrangement for the Column.
 * @param applyHorizontalPadding Flag to apply horizontal padding.
 * @param floatingActionButton Content for the floating action button.
 * @param content The content to display inside the column.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SharedColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    applyHorizontalPadding: Boolean = true,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        floatingActionButton = floatingActionButton,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = verticalArrangement,
            modifier = modifier
                .then(modifierOfParentColumns)
                .padding(
                    horizontal = if (applyHorizontalPadding) dimensionResource(R.dimen.horizontalScreenPadding) else 0.dp
                )
        ) { content() }
    }
}

/**
 * This composable creates a lazy column layout within a scaffold, with optional horizontal padding.
 *
 * @param modifier Modifier for the LazyColumn composable.
 * @param verticalArrangement Vertical arrangement for the LazyColumn.
 * @param floatingActionButton Content for the floating action button.
 * @param applyHorizontalPadding Flag to apply horizontal padding.
 * @param content The content to display inside the lazy column.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SharedLazyColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    floatingActionButton: @Composable () -> Unit = {},
    applyHorizontalPadding: Boolean = true,
    content: LazyListScope.() -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        floatingActionButton = floatingActionButton,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = verticalArrangement,
            contentPadding = PaddingValues(bottom = verticalArrangement.spacing),
            modifier = modifier
                .then(modifierOfParentColumns)
                .padding(
                    horizontal = if (applyHorizontalPadding) dimensionResource(R.dimen.horizontalScreenPadding) else 0.dp
                )
        ) { content() }
    }
}

private val modifierOfParentColumns = Modifier
    .systemBarsPadding()
    .padding(top = 2.dp)