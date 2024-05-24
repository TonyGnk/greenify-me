package com.example.greenifyme.ui.user.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.shared.ParentColumn
import com.example.greenifyme.ui.user.form.dialog.UserFormDialogMain
import com.example.greenifyme.ui.user.form.list.UserFormAppBar
import com.example.greenifyme.ui.user.form.list.UserFormBottomButtons
import com.example.greenifyme.ui.user.form.list.UserFormList

/**
 * This is the main composable for the User Form screen. It is responsible for rendering the entire screen.
 *
 * @param modifier The modifier to be applied to the composable. This can be used to adjust the layout, appearance, and behavior of this composable.
 */
@Composable
fun UserFormMain(modifier : Modifier = Modifier) {
	val model : UserFormModel = viewModel(factory = ViewModelProvider.Factory)
	val state by model.state.collectAsState()

	Surface(
		color = MaterialTheme.colorScheme.surfaceContainerHigh,
		modifier = modifier.fillMaxSize()
	) {
		if (state.showDialog) UserFormDialogMain(model, state)

		ParentColumn(
			verticalArrangement = Arrangement.spacedBy(4.dp),
		) {
			UserFormAppBar(model, state)
			UserFormList(model, state, Modifier.weight(1f))
			UserFormBottomButtons(model, state)
		}
	}
}
