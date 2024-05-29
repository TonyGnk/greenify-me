package com.example.greenifyme.ui.admin.home.charts

import android.annotation.SuppressLint
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.ui.shared.SharedCardText
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisGuidelineComponent
import com.patrykandpatrick.vico.compose.common.component.fixed
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.compose.common.shape.markerCornered
import com.patrykandpatrick.vico.core.cartesian.CartesianMeasureContext
import com.patrykandpatrick.vico.core.cartesian.HorizontalDimensions
import com.patrykandpatrick.vico.core.cartesian.Insets
import com.patrykandpatrick.vico.core.cartesian.marker.CartesianMarker
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.shape.Corner
import com.patrykandpatrick.vico.core.common.shape.Shape

/**
 * This composable function provides a layout for charts with consistent padding and spacing between elements.
 *
 * @param content The composable content to be displayed within the column.
 */
//@Composable
//fun SharedChartLayout(
//    content: @Composable ColumnScope.() -> Unit
//) {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier.padding(14.dp)
//    ) { content() }
//}

/**
 * This composable function displays a top bar for charts with a label and an optional right-side item.
 *
 * @param text The resource ID of the text to be displayed as the label.
 * @param rightItem The composable content to be displayed on the right side of the top bar. Default is an empty composable.
 */
@Composable
fun SharedChartTopBar(
    text: String,
    showExternalIcon: Boolean = false,
    rightItem: @Composable () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(31.dp)
    ) {
        SharedCardText(text)
        rightItem()
        if (showExternalIcon) Icon(
            painter = getVector(drawableValue = R.drawable.arrow_up_right),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

/**
 * This composable function displays a card with a consistent style for chart components.
 *
 * @param content The composable content to be displayed within the card. Default is an empty composable.
 */
@Composable
fun SharedChartCard(content: @Composable () -> Unit = {}) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(12),
        modifier = Modifier.fillMaxSize()
    ) { content() }
}

/**
 * This composable function creates a custom Cartesian marker for charts with optional display settings.
 * The marker can have extra top padding and can be shown or hidden.
 *
 * @param extraTopPadding The extra padding to be added at the top of the marker. Default is 3f.
 * @param show Indicates whether the marker should be shown. Default is true.
 * @return A customized CartesianMarker for chart components.
 */
@Composable
fun sharedMarker(
    extraTopPadding: Float = 3f,
    show: Boolean = true
): CartesianMarker {
    val labelBackgroundShape = Shape.markerCornered(Corner.FullyRounded)
    val labelBackground =
        rememberShapeComponent(
            Shape.markerCornered(Corner.FullyRounded),
            if (show) MaterialTheme.colorScheme.onPrimary else Color.Transparent
        )
    val label = rememberTextComponent(
        textSize = if (show) 14.sp else 0.sp,
        background = labelBackground,
        padding = Dimensions.of(8.dp, 4.dp),
        textAlignment = Layout.Alignment.ALIGN_CENTER,
        minWidth = TextComponent.MinWidth.fixed(30.dp),
    )
    val guideline = rememberAxisGuidelineComponent()
    return remember(label, guideline) {
        @SuppressLint("RestrictedApi")
        object : DefaultCartesianMarker(
            label = label,
            labelPosition = LabelPosition.AbovePoint,
        ) {
            override fun getInsets(
                context: CartesianMeasureContext,
                outInsets: Insets,
                horizontalDimensions: HorizontalDimensions,
            ) {
                with(context) {
                    if (labelPosition == LabelPosition.AroundPoint) return
                    outInsets.top += label.getHeight(context) + labelBackgroundShape.tickSizeDp.pixels + extraTopPadding
                }
            }
        }
    }
}