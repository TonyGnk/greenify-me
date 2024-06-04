package com.example.greenifyme.ui.admin.materials

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.SharedModelProvider
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.ui.shared.SharedAnimatedList
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedBackBehavior
import com.example.greenifyme.ui.shared.SharedColumn
import com.example.greenifyme.ui.user.form.dialog.CategoriesGrid

class AdminMaterialsActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                AdminEditingMaterials()
            }
        }
    }
}

@Composable
@Preview
private fun AdminEditingMaterials(isFakeRepository: Boolean = false) {
    val model: AdminEditMaterialsModel =
        viewModel(factory = SharedModelProvider.Factory(isFakeRepository))
    val state by model.state.collectAsState()
    val materials by model.totalMaterialsState.collectAsState()
    val activity = LocalContext.current as Activity

    SharedColumn(
        applyHorizontalPadding = false,
        floatingActionButton = {
            FloatingActionButton(
                onClick = model::onFabClick,
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    painter = getVector(drawableValue = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    ) {
        SharedAppBar(
            text = "Materials",
            backBehavior = SharedBackBehavior.Enable { activity.finish() },
        )
        AdminMaterialsGrid(materials, model::onItemClick)
    }

    if (state.showDialog) {
        Dialog(
            onDismiss = model::onDialogDismiss,
            onDelete = model::onDeleteMaterial,
            dialogType = state.dialogType,
            onGramFieldChange = model::onGramFieldChange,
            onPieceFieldChange = model::onPieceFieldChange,
            onNameFieldChange = model::onNameFieldChange,
            gramField = state.gramField,
            pieceField = state.pieceField,
            nameField = state.nameField,
            showCategoryPicker = state.showCategoryPicker,
            pickCategory = model::pickCategory,
            material = state.selectedMaterial,
            onAddMaterialFirst = model::onAddMaterialFirst,
        )
    }
}

@Composable
private fun AdminMaterialsGrid(
    materials: List<Material>, onItemClick: (Material) -> Unit
) {
    SharedAnimatedList(visible = materials.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .clip(RoundedCornerShape(getDimen(dimenValue = R.dimen.column_card_corner_radius)))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
        ) {
            items(items = materials, key = { it.materialId }) { material ->
                AccountListItem2(material) { onItemClick(material) }
            }
        }
    }
}

@Composable
private fun AccountListItem2(material: Material, onItemClick: () -> Unit) {
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent,
        ),
        leadingContent = {
            Icon(
                painter = getVector(material.category.icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(25.dp),
            )
        },
        modifier = Modifier.clickable { onItemClick() },
        headlineContent = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = getDimen(dimenValue = R.dimen.horizontalScreenPadding))
            ) {
                Text(material.name, style = MaterialTheme.typography.titleMedium)
            }

        },
    )
}

@Composable
private fun Dialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    dialogType: AddingMaterialDialogType,
    onGramFieldChange: (String) -> Unit,
    onPieceFieldChange: (String) -> Unit,
    onNameFieldChange: (String) -> Unit,
    gramField: String,
    pieceField: String,
    nameField: String,
    showCategoryPicker: Boolean,
    pickCategory: (RecyclingCategory) -> Unit,
    material: Material?,
    onAddMaterialFirst: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                getString(
                    when (dialogType) {
                        AddingMaterialDialogType.Details -> R.string.admin_edit_materials_dialog_details_title
                        AddingMaterialDialogType.New -> R.string.admin_edit_materials_dialog_new_title
                    }
                )
            )
        },
        text = {
            AnimatedSwitcher(
                selectedOption = showCategoryPicker,
                trueContent = {
                    CategoriesGrid(
                        onCategorySelected = pickCategory,
                        listItems = RecyclingCategory.entries
                    )
                },
                falseContent = {
                    when (dialogType) {
                        AddingMaterialDialogType.Details -> DialogContentDetails(material)
                        AddingMaterialDialogType.New -> DialogContentNew(
                            onGramFieldChange = onGramFieldChange,
                            onPieceFieldChange = onPieceFieldChange,
                            onNameFieldChange = onNameFieldChange,
                            gramField = gramField,
                            pieceField = pieceField,
                            nameField = nameField,
                        )
                    }
                }
            )

        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        confirmButton = {
            when (dialogType) {
                AddingMaterialDialogType.Details -> TextButton(onDelete) {
                    Text(getString(R.string.admin_edit_materials_dialog_delete))
                }

                AddingMaterialDialogType.New ->
                    if (!showCategoryPicker) {
                        Button(onAddMaterialFirst) {
                            Text(getString(R.string.admin_edit_materials_dialog_add))
                        }
                    }
            }
        }
    )
}

@Composable
private fun DialogContentDetails(material: Material?) {
    val pointsLine: String = when (material?.type) {
        is Pieces -> "${material.type.pointsPerPiece}points per piece"
        is Grams -> "${material.type.pointsPerGram}points per gram"
        is Both -> "${material.type.pointsPerPiece}points per piece, ${material.type.pointsPerGram} per gram"
        null -> ""
    }
    Text(
        text = pointsLine,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun DialogContentNew(
    onGramFieldChange: (String) -> Unit,
    onPieceFieldChange: (String) -> Unit,
    onNameFieldChange: (String) -> Unit,
    gramField: String,
    pieceField: String,
    nameField: String,
) {
    val focusRequesterForName = remember { FocusRequester() }
    val focusRequesterForGrams = remember { FocusRequester() }
    val focusRequesterForPieces = remember { FocusRequester() }
    Column(modifier = Modifier.height(314.dp)) {
        Field(
            label = "Name",
            value = nameField,
            onValueChange = onNameFieldChange,
            isGramsSelected = null,
            focusRequester = focusRequesterForName,
            onNextFocusRequester = focusRequesterForGrams,
            options = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Field(
            label = "Grams",
            value = gramField,
            onValueChange = onGramFieldChange,
            isGramsSelected = true,
            options = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            focusRequester = focusRequesterForGrams,
            onNextFocusRequester = focusRequesterForPieces
        )

        Spacer(modifier = Modifier.height(10.dp))

        Field(
            label = "Pieces",
            value = pieceField,
            onValueChange = onPieceFieldChange,
            isGramsSelected = false,
            options = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            focusRequester = focusRequesterForPieces

        )
        Spacer(modifier = Modifier.height(10.dp))

    }
}


@Composable
private fun Field(
    label: String,
    value: String,
    isGramsSelected: Boolean?,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onNextFocusRequester: FocusRequester? = null,
    options: KeyboardOptions
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        leadingIcon = {
            Icon(
                painter = getVector(
                    when (isGramsSelected) {
                        true -> R.drawable.scale
                        false -> R.drawable.water_bottle
                        null -> R.drawable.objects_column
                    }
                ),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onNext = {
                onNextFocusRequester?.requestFocus()
            }
        ),
        keyboardOptions = options,
        onValueChange = onValueChange,
        placeholder = {
            when (isGramsSelected) {
                true -> Text(
                    text = "Enter grams",
                    style = MaterialTheme.typography.bodyMedium
                )

                false -> Text(
                    text = "Enter pieces",
                    style = MaterialTheme.typography.bodyMedium
                )

                null -> Text(
                    text = "Enter name",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        shape = RoundedCornerShape(getDimen(dimenValue = R.dimen.column_card_corner_radius)),
        textStyle = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun AnimatedSwitcher(
    selectedOption: Boolean,
    trueContent: @Composable (() -> Unit),
    falseContent: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        selectedOption,
        transitionSpec = {
            scaleIn(
                initialScale = 0.9f,
                animationSpec = tween(400)
            ) + fadeIn(
                animationSpec = tween(400)
            ) togetherWith fadeOut(animationSpec = tween(300))
        },
        label = "Switcher FormDialogDestination Values",
        modifier = modifier
    ) { targetDestination ->
        when (targetDestination) {
            true -> trueContent()
            false -> falseContent()
        }
    }
}