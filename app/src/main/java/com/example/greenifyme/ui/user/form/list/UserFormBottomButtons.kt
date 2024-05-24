package com.example.greenifyme.ui.user.form.list

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.form.UserFormState

@Composable
fun UserFormBottomButtons(
	model : UserFormModel,
	state : UserFormState
) {
	val activity = LocalContext.current as Activity
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(
				horizontal = 2.dp,
				vertical = dimensionResource(R.dimen.horizontalScreenPadding) + 2.dp
			),
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Spacer(modifier = Modifier.weight(1f))
		TextButton(onClick = {
			model.setDialog(true)
		}) {
			Text(text = stringResource(state.strings.actionButtonsAdd))
		}
		Button(onClick = {
			model.submitForm(activity)
		}) {
			Text(text = "Submit")
		}
	}
}