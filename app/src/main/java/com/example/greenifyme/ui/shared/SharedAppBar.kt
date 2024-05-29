package com.example.greenifyme.ui.shared

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector

/**
 * This composable creates a shared app bar with optional animation for text.
 *
 * @param modifier Modifier for the Row composable.
 * @param isTextAnimated Flag to enable text animation.
 * @param isBackButtonVisible Flag to show/hide the back button.
 * @param onBackButtonPressed Callback for back button press.
 * @param text Text to display in the app bar.
 * @param rightSideRowModifier Modifier for the right side Row composable.
 * @param rightSideArrangement Arrangement for the right side Row composable.
 * @param rightSideContent Content for the right side of the app bar.
 */
@Composable
fun SharedAppBar(
    modifier: Modifier = Modifier,
    isTextAnimated: Boolean = false,
    isBackButtonVisible: Boolean = false,
    onBackButtonPressed: () -> Unit = {},
    text: String = "Label",
    rightSideRowModifier: Modifier = Modifier,
    rightSideArrangement: Arrangement.Horizontal = Arrangement.spacedBy(2.dp),
    rightSideContent: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = when (isBackButtonVisible) {
                    true -> getDimen(R.dimen.horizontalScreenPaddingSmall)
                    false -> 0.dp
                },
                end = if (isBackButtonVisible) getDimen(R.dimen.horizontalScreenPadding) else 0.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        if (isBackButtonVisible)
            IconButton(onClick = { onBackButtonPressed() }) {
                Icon(
                    getVector(R.drawable.angle_left),
                    contentDescription = getString(R.string.back),
                    modifier = Modifier
                        .size(22.dp)
                        .padding(1.dp)
                )
            }
        if (isTextAnimated) AnimatedGreeting(text)
        else StaticAppBarText(text)
        Spacer(modifier = Modifier.weight(1f))
        Row(
            horizontalArrangement = rightSideArrangement,
            verticalAlignment = Alignment.CenterVertically,
            modifier = rightSideRowModifier,
        ) {
            rightSideContent()
        }
    }
}

/**
 * This composable provides animated greeting text with a specified transition.
 *
 * @param changedValue The text value to animate.
 */
@Composable
private fun AnimatedGreeting(changedValue: String) {
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
        label = "Animated Greeting"
    ) {
        Text(
            text = it,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.W600,
        )

    }
}

/**
 * This composable provides static app bar text.
 *
 * @param text The text to display in the app bar.
 */
@Composable
private fun StaticAppBarText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.W600,
    )
}

