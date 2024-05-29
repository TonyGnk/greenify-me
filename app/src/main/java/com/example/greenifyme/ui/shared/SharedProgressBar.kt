package com.example.greenifyme.ui.shared

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SharedProgressBar(
    percent: Float,
    startingLabel: String = "",
    endingLabel: String = ""
) {


    val animatedValue by animateFloatAsState(
        targetValue = percent,
        animationSpec = tween(
            durationMillis = 900,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    val toNotBeExactlyZero = 0.0001f
    val antiWeight = maxOf(toNotBeExactlyZero, 1 - animatedValue)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.width(126.dp)
        ) {
            Box(modifier = Modifier.weight(animatedValue + toNotBeExactlyZero))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Text(
                    text = "${(animatedValue * 100).toInt()}%",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp)
                )
            }
            Box(modifier = Modifier.weight(antiWeight))
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .width(90.dp)
                .height(10.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Box(
                modifier = Modifier
                    .weight(animatedValue + toNotBeExactlyZero)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Box(
                modifier = Modifier
                    .weight(antiWeight)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            )
        }
        if (startingLabel.isNotEmpty() || endingLabel.isNotEmpty())
            Row(
                modifier = Modifier.width(106.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = startingLabel,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp)
                    )
                }
                Box(modifier = Modifier.weight(1f))
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = endingLabel,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp)
                    )
                }
            }
    }
}