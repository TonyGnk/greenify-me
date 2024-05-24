package com.example.greenifyme.compose_utilities

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp

@Composable
fun getString(stringValue: Int): String = stringResource(stringValue)


@Composable
fun getVector(drawableValue: Int): Painter = painterResource(drawableValue)


@Composable
fun getDimen(dimenValue: Int): Dp = dimensionResource(dimenValue)