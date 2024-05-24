package com.example.greenifyme.ui.database_manager

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

// This is a custom data class that represents a navigation item in the DBManager.
/// It gets a destination (Enum), a title R (=R.strings.something), a
// filled (=R.drawables.something) and an outline (=R.drawables.something) vector.
// Automatically, it creates a label and icon for the navigation item.
data class DBManagerNavItem(
	val destination : DBManagerNavDestination, // NEEDED
	val titleRes : Int, // NEEDED
	val label : @Composable () -> Unit = { // NO NEEDED
		Text(stringResource(id = titleRes))
	},
	val filledVector : Int, // NEEDED
	val filledIcon : @Composable () -> Unit = { // NO NEEDED
		Icon(
			painter = painterResource(id = filledVector),
			contentDescription = stringResource(id = titleRes)
		)
	},
	val outlineVector : Int, // NEEDED
	val outlineIcon : @Composable () -> Unit =
		{
			Icon(
				painter = painterResource(id = outlineVector),
				contentDescription = stringResource(id = titleRes)
			)
		}
)