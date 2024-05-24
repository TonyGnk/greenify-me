package com.example.greenifyme.ui.shared

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import com.example.greenifyme.R

@Composable
fun ParentColumn(
	modifier : Modifier = Modifier,
	verticalArrangement : Arrangement.Vertical = Arrangement.spacedBy(10.dp),
	applyHorizontalPadding : Boolean = true,
	content : @Composable ColumnScope.() -> Unit,
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
	modifier : Modifier = Modifier,
	verticalArrangement : Arrangement.Vertical = Arrangement.spacedBy(10.dp),
	applyHorizontalPadding : Boolean = true,
	content : LazyListScope.() -> Unit,
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