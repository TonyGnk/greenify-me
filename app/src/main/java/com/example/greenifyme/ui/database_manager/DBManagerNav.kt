package com.example.greenifyme.ui.database_manager

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.ui.database_manager.account.AccountMain
import com.example.greenifyme.ui.database_manager.material.MaterialMain
import com.example.greenifyme.ui.database_manager.record.RecordMain

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
        Column(
            modifier = Modifier
                .weight(1f) // flex= 1, cover all the available (height) space of parent
                .fillMaxWidth()
                .padding(top = 8.dp, start = 15.dp, end = 15.dp)
                .statusBarsPadding() //Add dynamic padding based the phone's status bar height
        ) {
            when (uiState.destination) {
                DBManagerNavDestination.Account -> AccountMain()
                DBManagerNavDestination.Record -> RecordMain()
                DBManagerNavDestination.Material -> MaterialMain()
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

