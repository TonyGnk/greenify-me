package com.example.greenifyme.ui.database_manager.content_shared.bottom_sheet

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SheetTextField(
	value : String,
	onValueChange : (String) -> Unit,
	label : Int,
	modifier : Modifier = Modifier,
	keyboardOptions : KeyboardOptions = KeyboardOptions.Default,
	leadingIcon : @Composable () -> Unit = {},
) {
	OutlinedTextField(
		value = value,
		onValueChange = onValueChange,
		keyboardOptions = keyboardOptions,
		placeholder = {
			Text(
				stringResource(label),
				style = MaterialTheme.typography.bodyMedium
			)
		},
		textStyle = MaterialTheme.typography.bodyMedium,
		colors = OutlinedTextFieldDefaults.colors(
			focusedBorderColor = Color.Transparent,
			unfocusedBorderColor = Color.Transparent,
			focusedContainerColor = Color.Transparent,
			unfocusedContainerColor = Color.Transparent,
		),
		shape = CircleShape,
		enabled = true,
		leadingIcon = leadingIcon,
		singleLine = true,
		modifier = modifier.height(49.dp)
	)
}
