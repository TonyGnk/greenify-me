package com.example.greenifyme.ui.user.form.dialog

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.greenifyme.ui.user.form.FormDialogDestination

/**
 * This composable function is responsible for switching between different dialog destinations with animations.
 *
 * @param dialogDestination The destination of the dialog. It determines which content to display.
 * @param categoryContent A composable function that provides the content for the category dialog destination.
 * @param materialContent A composable function that provides the content for the material dialog destination.
 * @param quantityContent A composable function that provides the content for the quantity dialog destination.
 * @param modifier A Modifier that can be used to adjust the layout or other visual properties of the composable.
 */
@Composable
fun AnimatedDialogSwitcher(
    dialogDestination: FormDialogDestination,
    categoryContent: @Composable (() -> Unit),
    materialContent: @Composable (() -> Unit),
    quantityContent: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        dialogDestination,
        transitionSpec = {
            scaleIn(
                initialScale = 0.93f,
                animationSpec = tween(450)
            ) + fadeIn(
                animationSpec = tween(450)
            ) togetherWith fadeOut(animationSpec = tween(300))
        },
        label = "Switcher FormDialogDestination Values",
        modifier = modifier
    ) { targetDestination ->
        when (targetDestination) {
            FormDialogDestination.CATEGORY -> categoryContent()
            FormDialogDestination.MATERIAL -> materialContent()
            FormDialogDestination.QUANTITY -> quantityContent()
        }
    }
}