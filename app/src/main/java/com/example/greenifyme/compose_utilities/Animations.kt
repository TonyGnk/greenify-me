package com.example.greenifyme.compose_utilities

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith

fun <S> transitionSpec(): AnimatedContentTransitionScope<S>.() -> ContentTransform = {
    scaleIn(
        initialScale = 0.93f,
        animationSpec = tween(400)
    ) + fadeIn(
        animationSpec = tween(400)
    ) togetherWith fadeOut(animationSpec = tween(300))
}