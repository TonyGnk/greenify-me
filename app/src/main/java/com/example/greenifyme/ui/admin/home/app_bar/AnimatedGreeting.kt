package com.example.greenifyme.ui.admin.home.app_bar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AnimatedGreeting(changedValue: Int) {
    val fadeOutSpec = fadeIn(
        animationSpec = tween(600)
    ) + scaleIn(
        animationSpec = tween(900),
        initialScale = 0.6f
    ) togetherWith fadeOut(animationSpec = tween(600)) +
            scaleOut(animationSpec = tween(600), targetScale = 0.7f)

    AnimatedContent(
        transitionSpec = { fadeOutSpec },
        targetState = changedValue,
        label = "AnimatedGreeting"
    ) {
        Text(
            text = stringResource(it),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.W600,
        )

    }
}