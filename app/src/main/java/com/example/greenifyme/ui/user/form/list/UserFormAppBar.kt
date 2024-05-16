package com.example.greenifyme.ui.user.form.list

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.form.UserFormState

@Composable
fun UserFormAppBar(
	model : UserFormModel,
	state : UserFormState
) {
	val activity = LocalContext.current as Activity

	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			text = stringResource(state.strings.appBarLabel),
			style = MaterialTheme.typography.headlineSmall,
			fontWeight = FontWeight.W600,
		)
		Spacer(modifier = Modifier.weight(1f))
		TextButton(onClick = { model.quitForm(activity) }) {
			Text(text = stringResource(state.strings.appBarCancel))
		}
	}
}