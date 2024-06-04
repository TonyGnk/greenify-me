package com.example.greenifyme.ui.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SharedAnimatedList(
    visible: Boolean,
    content: @Composable ColumnScope.() -> Unit
) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = visible,
        enter = fadeIn(tween(370, easing = FastOutSlowInEasing)) + slideInVertically(
            tween(350, easing = FastOutSlowInEasing),
            initialOffsetY = { it / 5 },
        ),
    ) {
        Column(Modifier.fillMaxSize()) {
            content()
        }
    }
}