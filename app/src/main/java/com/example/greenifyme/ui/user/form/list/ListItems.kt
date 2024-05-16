package com.example.greenifyme.ui.user.form.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.form.UserFormState

@Composable
fun UserFormList(model : UserFormModel, state : UserFormState, modifier : Modifier = Modifier) {
	LazyColumn(
		contentPadding = PaddingValues(bottom = 10.dp),
		verticalArrangement = Arrangement.spacedBy(10.dp),
		modifier = modifier
	) {
	}
}