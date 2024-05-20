package com.example.greenifyme.ui.user.form.dialog

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.ui.user.form.FormDialogDestination
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.form.UserFormState

/**
 * This composable function is responsible for displaying the main dialog of the user form.
 *
 * @param model The UserFormModel which contains the business logic for the user form.
 * @param state The UserFormState which contains the current state of the user form.
 */
@Composable
fun UserFormDialogMain(model : UserFormModel, state : UserFormState) {
	AlertDialog(
		title = {
			AnimatedDialogSwitcher(
				state.dialogDestination,
				{ Text(getString(FormDialogDestination.CATEGORY.title)) },
				{ Text(getString(FormDialogDestination.MATERIAL.title)) },
				{ Text(getString(FormDialogDestination.QUANTITY.title)) }
			)
		},
		text = {
			AnimatedDialogSwitcher(
				state.dialogDestination,
				{ RecyclingCategoriesGrid(model, state) },
				{ MaterialsListLayout(model, state) },
				{ SelectQuantity(model, state) },
				Modifier.height(304.dp)
			)
		},
		dismissButton = {
			TextButton(onClick = { model.onDismissButton() })
			{
				AnimatedDialogSwitcher(
					state.dialogDestination,
					{ Text(getString(state.strings.dialogCancel)) },
					{ Text(getString(state.strings.dialogBack)) },
					{ Text(getString(state.strings.dialogBack)) }
				)
			}
		},
		confirmButton = {
			AnimatedDialogSwitcher(
				state.dialogDestination,
				{ }, // Don't show when adding a category
				{ }, // Don't show when adding a material
				{
					Button(onClick = { })
					{
						Text(getString(state.strings.dialogAdd))
					}
				}
			)
		},
		onDismissRequest = { model.setDialog(false) },
		containerColor = MaterialTheme.colorScheme.surfaceContainer,
		tonalElevation = 0.dp,
		modifier = Modifier.width(400.dp)
	)
}


