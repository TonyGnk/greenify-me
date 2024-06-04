package com.example.greenifyme.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen

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
    modifier: Modifier = Modifier,
    modifierContent: Modifier = Modifier.padding(
        top = 10.dp, start = 15.dp, end = 15.dp, bottom = 15.dp
    ),
    applyHorizontalPadding: Boolean = false,
    horizontalPadding: Dp = dimensionResource(R.dimen.activity_horizontal_margin),
    height: Dp? = null,
    topBarType: SharedAppBarType = SharedAppBarType.NoTopBar,
    behavior: SharedBehavior = SharedBehavior.Static,
    color: Color = MaterialTheme.colorScheme.surfaceContainerLowest,
    rightAppBarItem: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        color = color,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(getDimen(R.dimen.column_card_corner_radius)))
            .padding(
                horizontal = when (applyHorizontalPadding) {
                    true -> horizontalPadding
                    false -> 0.dp
                }
            )
            .then(
                if (height == null) Modifier
                else Modifier.height(height)
            )
            .then(
                when (behavior) {
                    is SharedBehavior.Clickable -> Modifier.clickable(onClick = behavior.onClick)
                    is SharedBehavior.Static -> Modifier
                }
            )

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifierContent
        ) {
            if (topBarType is SharedAppBarType.Enable) {
                SharedChartTopBar(
                    text = topBarType.text,
                    showExternalIcon = behavior is SharedBehavior.Clickable,
                    rightItem = rightAppBarItem
                )
            }
            content()
        }
    }
}

/**
 * This composable provides text to display inside a shared card.
 *
 * @param text The text to display inside the card.
 */
@Composable
fun SharedCardText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.W600,
        modifier = modifier
    )
}

sealed class SharedAppBarType {
    data class Enable(val text: String) : SharedAppBarType()

    data object NoTopBar : SharedAppBarType()
}

sealed class SharedBehavior {
    data class Clickable(val onClick: () -> Unit) : SharedBehavior()

    data object Static : SharedBehavior()
}