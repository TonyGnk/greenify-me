package com.example.greenifyme.compose_utilities

import android.graphics.drawable.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*

@Composable
fun getString(stringValue : Int) : String = stringResource(stringValue)



@Composable
fun getVector(drawableValue : Int) : Painter = painterResource(drawableValue)



@Composable
fun getDimen(dimenValue : Int) : Dp = dimensionResource(dimenValue)