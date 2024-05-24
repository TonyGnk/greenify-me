package com.example.greenifyme.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R

@Composable
fun ParentColumn(
        modifier: Modifier = Modifier,
        verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
        applyHorizontalPadding: Boolean = true,
        content: @Composable ColumnScope.() -> Unit,
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


@Composable
fun ParentLazyColumn(
        modifier: Modifier = Modifier,
        verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
        applyHorizontalPadding: Boolean = true,
        content: LazyListScope.() -> Unit,
) {
    LazyColumn(
            verticalArrangement = verticalArrangement,
            modifier = modifier
					.then(modifierOfParentColumns)
					.padding(
							horizontal = if (applyHorizontalPadding) dimensionResource(R.dimen.horizontalScreenPadding) else 0.dp
					)
    ) { content() }
}

private val modifierOfParentColumns = Modifier
		.systemBarsPadding()
		.padding(top = 2.dp)