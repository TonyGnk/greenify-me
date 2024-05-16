package com.example.greenifyme.ui.user.form.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.form.UserFormState

/**
 * A composable function that displays a grid of recycling categories.
 *
 * @param model The [UserFormModel] which contains the business logic for the user form.
 * @param state The [UserFormState] which contains the current state of the user form, including the list of recycling categories.
 * This model provides the list of recycling categories to be displayed in the grid.
 * When a category is selected, the model's `onCategorySelected` function is called with the selected category.
 */
@Composable
fun RecyclingCategoriesGrid(model : UserFormModel, state : UserFormState) {
	val listItems = state.recyclingCategories
	LazyVerticalGrid(
		columns = GridCells.Fixed(3),
		verticalArrangement = Arrangement.spacedBy(18.dp),
		horizontalArrangement = Arrangement.SpaceBetween,
		modifier = Modifier.fillMaxWidth(),
	) {
		items(items = listItems) { item->
			CategoryGridItem(
				item = item,
				onClick = { model.onCategorySelected(item) },
			)
		}
	}
}



/**
 * A composable function that displays a grid item for a recycling category.
 *
 * @param item The [RecyclingCategory] to be displayed. This includes the icon and description of the category.
 * @param onClick A lambda function that will be executed when the grid item is clicked.
 */
@Composable
private fun CategoryGridItem(
	item : RecyclingCategory, onClick : () -> Unit,
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(3.dp),
		modifier = Modifier.clickable { onClick() }
	) {
		Button(
			onClick = onClick,
			colors = ButtonDefaults.buttonColors(
				containerColor = MaterialTheme.colorScheme.secondary,
			),
			shape = RoundedCornerShape(20.dp),
			contentPadding = PaddingValues(12.dp),
		) {
			Icon(
				painter = getVector(item.icon),
				contentDescription = getString(item.description),
				tint = MaterialTheme.colorScheme.onSecondary,
				modifier = Modifier.size(42.dp)
			)
		}
		Text(
			text = getString(item.description),
			fontSize = 13.sp,
		)
	}
}

