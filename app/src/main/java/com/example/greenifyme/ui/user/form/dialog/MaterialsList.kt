package com.example.greenifyme.ui.user.form.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.data.Material
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.form.UserFormState

/**
 * A composable function that displays a list of materials in a lazy column.
 *
 * @param model The UserFormModel which contains the business logic for the user form.
 * @param state The UserFormState which contains the current state of the user form, including the list of materials.
 */
@Composable
fun MaterialsList(model: UserFormModel, state: UserFormState) {
    val listItems = state.materials
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(items = listItems, key = { it.materialId }
        ) { item ->
            MaterialRowItem(
                item = item,
                onClick = { model.selectMaterial(item) }
            )
        }
    }
}


/**
 * A composable function that displays a single row item in the materials list.
 *
 * @param item The Material object to be displayed. It contains information about the material such as its category and name.
 * @param onClick A lambda function that will be executed when the row item is clicked. It is typically used to handle the event of selecting a material.
 */
@Composable
private fun MaterialRowItem(
    item: Material, onClick: () -> Unit
) {
    ListItem(
        leadingContent = {
            Icon(
                painter = getVector(item.category.icon),
                contentDescription = getString(item.category.description),
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(30.dp)
            )
        },
        headlineContent = {
            Text(text = item.name, fontSize = 17.sp)
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .clickable { onClick() }
    )
}