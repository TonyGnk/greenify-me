package com.example.greenifyme.ui.database_manager

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.ui.database_manager.account.AccountMain

// This is the navigation structure of the DBManager.
// Contains the NavigationBar and the destination that are navigated to.
// Currently, only the Account screen is implemented.
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DBManagerNavigation() {
    //The 2 below lines are the most common thing in compose and are always used like this
    //Create a instance of the view model
    val model = DBManagerNavModel()

    // Collect the data of model as state (= I viewmodel change the items,
    // the UI knows it and updates)
    val uiState by model.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize() //Cover all screen
            .background( //Set background color
                MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
            )
    ) {
        var state by remember {
            mutableStateOf(uiState.destination)
        }
        Column(
            modifier = Modifier
                .weight(1f) // flex= 1, cover all the available (height) space of parent
                .fillMaxWidth()
                .padding(top = 8.dp, start = 15.dp, end = 15.dp)
                .statusBarsPadding() //Add dynamic padding based the phone's status bar height
        ) {
            AnimatedContent(
                state,
                transitionSpec = {
                    scaleIn(
                        animationSpec = tween(300),
                        transformOrigin = when (state) {
                            NavDestination.Account -> TransformOrigin(0.1F, 1F)
                            NavDestination.Track -> TransformOrigin(0.365F, 1F)
                            NavDestination.TrackedMaterial -> TransformOrigin(0.64F, 1F)
                            NavDestination.Material -> TransformOrigin(0.93F, 1F)
                        },

                        initialScale = 0.9f
                    ) togetherWith fadeOut(animationSpec = tween(100))
                },
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    state = when (state) {
                        NavDestination.Account -> NavDestination.Account
                        NavDestination.Material -> NavDestination.Material
                        NavDestination.TrackedMaterial -> NavDestination.TrackedMaterial
                        NavDestination.Track -> NavDestination.Track
                    }
                },
                label = "Animated Content"
            ) { targetState ->
                when (targetState) {
                    NavDestination.Account -> AccountMain()
                    NavDestination.Material -> LargeTextAtCenter()
                    NavDestination.TrackedMaterial -> LargeTextAtCenter()
                    NavDestination.Track -> LargeTextAtCenter()
                }
            }
        }
        NavigationBar(
            containerColor = Color.Transparent, //Remove default background color
            tonalElevation = 0.dp //Remove shadow
            //We don't specify weight(1f) here because
            // we want the NavigationBar to take only the space it needs
        ) {
            for (item in uiState.navigationList) { //for every navigation item
                val selected = uiState.destination == item.destination
                NavigationBarItem(
                    icon = { //If selected, show filled icon, else show outline icon
                        if (selected) item.filledIcon()
                        else item.outlineIcon()
                    },
                    // We use curly braces to pass a Composable
                    label = { item.label() },
                    selected = selected,
                    // Pass the view model's navigateTo function to the onClick
                    onClick = {
                        state = item.destination
                        model.navigateTo(item.destination)
                    }
                )
            }
        }
    }
}

// This a simple Composable that shows (a given) text (Default: "This database hasn't been implemented yet")
// at the center of the screen.
@Composable
fun LargeTextAtCenter(textResource: Int = R.string.database_not_implemented) {
    Column(
        // Vertically center the text
        verticalArrangement = Arrangement.Center,
        // Horizontally center the text
        horizontalAlignment = Alignment.CenterHorizontally,
        // Cover all the available space
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textResource),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

