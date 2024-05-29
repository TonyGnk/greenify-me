package com.example.greenifyme.ui.user.form.dialog

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.ui.database_manager.DBManagerActivity
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
fun UserFormDialogMain(model: UserFormModel, state: UserFormState) {
    val context = LocalContext.current as Activity
    AlertDialog(
        title = {
            Row {
                Text(getString(state.strings.dialogSelect))
                AnimatedDialogSwitcher(
                    state.dialogDestination,
                    { Text(getString(FormDialogDestination.CATEGORY.title)) },
                    { Text(getString(FormDialogDestination.MATERIAL.title)) },
                    { Text(getString(FormDialogDestination.QUANTITY.title)) }
                )
            }
        },
        text = {
            AnimatedDialogSwitcher(
                state.dialogDestination,
                { CategoriesGrid(model, state) },
                { MaterialsList(model, state) },
                { QuantityForm(model, state) },
                Modifier.height(314.dp)
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
                {
                    Button(onClick = {
                        context.startActivity(
                            Intent(
                                context,
                                DBManagerActivity::class.java
                            )
                        )
                    })
                    {
                        Text("DB")
                    }
                }, // Don't show when adding a material
                {
                    //Open Database Manager Activity

                    Button(onClick = { model.addTrack() })
                    {
                        Text(getString(state.strings.dialogAdd))
                    }
                }
            )
        },
        onDismissRequest = { model.setDialog(false) },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 0.dp,
        modifier = Modifier.width(406.dp)
    )
}



