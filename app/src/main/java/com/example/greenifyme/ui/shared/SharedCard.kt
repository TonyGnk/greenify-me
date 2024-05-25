package com.example.greenifyme.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R

/**
 * This composable creates a shared card with optional horizontal padding and customizable height.
 *
 * @param applyHorizontalPadding Flag to apply horizontal padding.
 * @param horizontalPadding Horizontal padding value.
 * @param height Height of the card.
 * @param color Background color of the card.
 * @param content The content to display inside the card.
 */
@Composable
fun SharedCard(
    applyHorizontalPadding: Boolean = false,
    horizontalPadding: Dp = dimensionResource(R.dimen.activity_horizontal_margin),
    height: Dp? = null,
    color: Color = MaterialTheme.colorScheme.surfaceContainerLowest,
    content: @Composable () -> Unit
) {
    Surface(
        color = color,
        shape = RoundedCornerShape(30.dp),
        //shadowElevation = 1.dp,
        modifier =
        if (height == null) Modifier
            .fillMaxWidth()
            .padding(
                horizontal = when (applyHorizontalPadding) {
                    true -> horizontalPadding
                    false -> 0.dp
                }
            )
        else
            Modifier
                .fillMaxWidth()
                .height(height)
                .padding(
                    horizontal = when (applyHorizontalPadding) {
                        true -> horizontalPadding
                        false -> 0.dp
                    }
                )
    ) {
        content()
    }
}

/**
 * This composable provides text to display inside a shared card.
 *
 * @param text The text to display inside the card.
 */
@Composable
fun SharedCardText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.W600,
    )
}