package com.example.greenifyme.ui.database_manager.material

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.greenifyme.data.Material

@Composable
fun MaterialListText(
	material : Material,
	modifier : Modifier
) {
	Text(
		text = material.materialId.toString() + "     "
				+ material.name + " | " + material.category.description,
		style = MaterialTheme.typography.bodyMedium,
		fontWeight = if (material.hasSubcategories) FontWeight.W900 else FontWeight.W300,
		modifier = modifier
	)
}