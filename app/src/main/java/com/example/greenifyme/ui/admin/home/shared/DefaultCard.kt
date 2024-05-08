package com.example.greenifyme.ui.admin.home.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultCard(
    horizontalPadding: Dp,
    height: Dp? = null,
    content: @Composable () -> Unit
) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(30.dp),
        shadowElevation = 1.dp,
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