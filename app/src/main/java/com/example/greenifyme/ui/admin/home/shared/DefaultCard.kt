package com.example.greenifyme.ui.admin.home.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R

@Composable
fun DefaultCard(
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
            .padding(horizontal = horizontalPadding)
        else
            Modifier
                .fillMaxWidth()
                .height(height)
                .padding(horizontal = horizontalPadding)
    ) {
        content()
    }
}