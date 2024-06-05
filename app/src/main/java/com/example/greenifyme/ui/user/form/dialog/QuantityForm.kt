package com.example.greenifyme.ui.user.form.dialog

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.OptionsType
import com.example.greenifyme.data.Pieces

@Composable
@Preview(showBackground = true)
fun QuantityFormPreview() {
    ComposeTheme {
        QuantityForm(
            Grams(23f), true, {}, {}, "", {}
        )
    }
}

@Composable
fun QuantityForm(
    options: OptionsType,
    isGramsSelected: Boolean,
    onDialogQuantityChangeSelection: (Int) -> Unit,
    onDialogQuantityQueryChange: (String) -> Unit,
    query: String,
    onEnter: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        if (options is Both) {
            SegmentedButtons(
                isGramsSelected = isGramsSelected,
                onDialogQuantityChangeSelection = onDialogQuantityChangeSelection
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            leadingIcon = {
                AnimatedSwitcher(
                    selectedOption = isGramsSelected,
                    gramsContent = {
                        Icon(
                            painter = getVector(drawableValue = R.drawable.scale),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    piecesContent = {
                        Icon(
                            painter = getVector(drawableValue = R.drawable.water_bottle),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )
            },
            onValueChange = onDialogQuantityQueryChange,
            placeholder = {
                AnimatedSwitcher(
                    selectedOption = isGramsSelected,
                    gramsContent = {
                        Text(
                            text = if (options is Grams) "Enter grams" else "Enter pieces",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    piecesContent = {
                        Text(
                            text = "Enter pieces",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
            },
            shape = RoundedCornerShape(getDimen(dimenValue = R.dimen.column_card_corner_radius)),
            keyboardActions = KeyboardActions(
                onDone = { onEnter() }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
        )


        when (options) {
            is Grams -> {
                Text(
                    text = "*For every gram you get ${options.pointsPerGram} point",
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            is Pieces -> {
                Text(
                    text = "*For every piece you get ${options.pointsPerPiece} point",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            is Both -> {
                if (isGramsSelected) {
                    Text(
                        text = "*For every gram you get ${options.pointsPerGram} point",
                        style = MaterialTheme.typography.bodySmall,
                    )
                } else {
                    Text(
                        text = "*For every piece you get ${options.pointsPerPiece} point",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SegmentedButtons(
    isGramsSelected: Boolean,
    onDialogQuantityChangeSelection: (Int) -> Unit
) {
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        SegmentedButton(
            shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2),
            onClick = { onDialogQuantityChangeSelection(0) },
            selected = isGramsSelected,
            modifier = Modifier.height(50.dp),
        ) {
            Text(getString(stringValue = R.string.user_form_dialog_quantity_option_grams))
        }
        SegmentedButton(
            shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2),
            onClick = { onDialogQuantityChangeSelection(1) },
            selected = !isGramsSelected,
            modifier = Modifier.height(50.dp),
        ) {
            Text(getString(stringValue = R.string.user_form_dialog_quantity_option_pieces))
        }
    }
}

@Composable
private fun AnimatedSwitcher(
    selectedOption: Boolean,
    gramsContent: @Composable (() -> Unit),
    piecesContent: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        selectedOption,
        transitionSpec = {
            scaleIn(
                initialScale = 0.5f,
                animationSpec = tween(300)
            ) + fadeIn(
                animationSpec = tween(300)
            ) togetherWith fadeOut(animationSpec = tween(300))
        },
        label = "Switcher FormDialogDestination Values",
        modifier = modifier
    ) { targetDestination ->
        when (targetDestination) {
            true -> gramsContent()
            false -> piecesContent()
        }
    }
}