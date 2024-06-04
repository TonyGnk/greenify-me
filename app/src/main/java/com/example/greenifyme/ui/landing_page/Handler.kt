package com.example.greenifyme.ui.landing_page

import android.content.Intent
import android.content.res.Resources
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getVector
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


enum class ColorType { Left, None, Right }

@Composable
fun DraggableHandleBox(intentSampleAdmin: Intent, intentSampleDBManager: Intent) {
    val context = LocalContext.current
    var isNeedColorChange by remember { mutableStateOf(ColorType.None) }
    val backgroundColor by animateColorAsState(
        when (isNeedColorChange) {
            ColorType.Left -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
            ColorType.Right -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
            ColorType.None -> MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        },
        animationSpec = tween(
            durationMillis = 1200,
            easing = LinearEasing
        ), label = ""
    )
    var isNeedColorChangeBasic by remember { mutableStateOf(false) }
    val backgroundColorBasic by animateColorAsState(
        when (isNeedColorChangeBasic) {
            true -> MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f)
            false -> MaterialTheme.colorScheme.onPrimary.copy(alpha = 1f)
        },
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing
        ), label = ""
    )


    val padding = 12.dp
    val boxWidth = LocalConfiguration.current.screenWidthDp.dp - padding * 2

    val handleWidth = 100.dp
    val initialOffsetX = remember { (boxWidth - handleWidth) / 2 }
    val animatedOffsetX = remember { Animatable(initialOffsetX.toPx()) }
    val coroutineScope = rememberCoroutineScope()

    val hideIconLeft = remember {
        mutableStateOf(false)
    }
    val hideIconRight = remember {
        mutableStateOf(false)
    }

    val animatedText = remember { mutableStateOf("") }
    val textAlpha = remember { Animatable(1f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(padding)
            .background(backgroundColor, CircleShape)
            .clip(CircleShape)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        coroutineScope.launch {
                            val handlePosition = animatedOffsetX.value
                            val leftEdgeThreshold = 0.dp.toPx()
                            val rightEdgeThreshold = boxWidth.toPx() - handleWidth.toPx()

                            // isNeedColorChangeBasic = false
                            hideIconLeft.value = false
                            hideIconRight.value = false
                            animatedText.value = ""
                            isNeedColorChange = ColorType.None


                            when {
                                handlePosition - 70 <= leftEdgeThreshold -> {
                                    println("Left SIDE PRESSED")
                                    coroutineScope.launch {
                                        isNeedColorChangeBasic = false
                                        delay(250)
                                        context.startActivity(intentSampleDBManager)
                                    }
                                }

                                handlePosition + 70 >= rightEdgeThreshold -> {
                                    println("Right SIDE PRESSED")
                                    coroutineScope.launch {
                                        isNeedColorChangeBasic = false
                                        delay(250)
                                        context.startActivity(intentSampleAdmin)
                                    }
                                }
                            }

                            if (handlePosition - 70 > leftEdgeThreshold && handlePosition + 70 < rightEdgeThreshold) {
                                isNeedColorChangeBasic = false

                            }

                            animatedOffsetX.animateTo(
                                targetValue = initialOffsetX.toPx(),
                                animationSpec = tween(durationMillis = 400)
                            )
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    coroutineScope.launch {
                        val newValue = animatedOffsetX.value + dragAmount.x
                        animatedOffsetX.snapTo(
                            newValue.coerceIn(
                                0f,
                                boxWidth.toPx() - handleWidth.toPx()
                            )
                        )
                        val leftEdgeThreshold = 0.dp.toPx()
                        val rightEdgeThreshold = boxWidth.toPx() - handleWidth.toPx()
                        val center = boxWidth.toPx() / 2 - handleWidth.toPx() / 2

                        if (newValue <= center) {
                            isNeedColorChange = ColorType.Left
                            hideIconLeft.value = true
                            hideIconRight.value = false
                        }
                        if (newValue >= center) {
                            isNeedColorChange = ColorType.Right
                            hideIconRight.value = true
                            hideIconLeft.value = false
                        }

                        isNeedColorChangeBasic =
                            newValue - 100 <= leftEdgeThreshold || newValue + 100 >= rightEdgeThreshold


                        when {
                            newValue - 150 <= leftEdgeThreshold -> {
                                animatedText.value = "Database Manager"
                            }

                            newValue + 150 >= rightEdgeThreshold -> {
                                animatedText.value = "Presentation"
                            }

                            else -> {
                                animatedText.value = ""
                            }
                        }
                    }
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            AnimatedContent(
                hideIconLeft.value,
                transitionSpec = {
                    scaleIn(
                        initialScale = 0.6f,
                        animationSpec = tween(500)
                    ) + fadeIn(
                        animationSpec = tween(500)
                    ) togetherWith fadeOut(animationSpec = tween(400))
                },
                label = "Switcher FormDialogDestination Values",
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { context.startActivity(intentSampleDBManager) }
                ) {
                    Icon(
                        painter =
                        when (it) {
                            true -> getVector(drawableValue = R.drawable.trash)
                            false -> getVector(drawableValue = R.drawable.back_up)
                        },
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(47.dp)
                            .padding(6.dp)
                    )
                }
            }
            AnimatedContent(
                animatedText.value,
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(600)
                    ) togetherWith fadeOut(animationSpec = tween(600))
                },
                label = "Switcher FormDialogDestination Values",
            ) {
                Text(
                    text = it, modifier = Modifier.alpha(textAlpha.value),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            AnimatedContent(
                hideIconRight.value,
                transitionSpec = {
                    scaleIn(
                        initialScale = 0.6f,
                        animationSpec = tween(500)
                    ) + fadeIn(
                        animationSpec = tween(500)
                    ) togetherWith fadeOut(animationSpec = tween(400))
                },
                label = "Switcher FormDialogDestination Values",
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { context.startActivity(intentSampleAdmin) }
                ) {
                    Icon(
                        painter =
                        when (it) {
                            true -> getVector(drawableValue = R.drawable.trash)
                            false -> getVector(drawableValue = R.drawable.chart_user)
                        },
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(47.dp)
                            .padding(6.dp)
                    )
                }
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .offset { IntOffset(animatedOffsetX.value.roundToInt(), 0) }
                //.aspectRatio(1f)
                //    .size(100.dp)
                .size(handleWidth, 100.dp)
            //  .background(backgroundColor, CircleShape)
        ) {
            Icon(
                painter = getVector(drawableValue = R.drawable.water_bottle),
                contentDescription = "",
                tint = backgroundColorBasic,
                modifier = Modifier.size(43.dp)
            )
        }
    }
}


fun Dp.toPx(): Float = this.value * Resources.getSystem().displayMetrics.density