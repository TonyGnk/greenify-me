package com.example.greenifyme.ui.user.form.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.OptionsType
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.form.UserFormState

@Composable
fun QuantityForm(model: UserFormModel, state: UserFormState) {

    val typeOfOptions: OptionsType = Pieces(23)// selectedMaterial.options.type

    val pointsPerPiece = 23
    val pointsPerGram = 23

    val textFieldContent = remember { mutableStateOf("") }

    Column {
        if (typeOfOptions is Grams) {
            Text(text = "Weight Options Available with points per gram: $pointsPerGram")
        } else if (typeOfOptions is Grams) {
            Text(text = "Pieces Options Available with points per piece: $pointsPerPiece")
        } else if (typeOfOptions is Both) {
            Text(text = "All Options Available with points per piece: $pointsPerPiece and points per gram: $pointsPerGram")
        }

        OutlinedTextField(
            value = textFieldContent.value,
            onValueChange = { textFieldContent.value = it },
            placeholder = {
                Text("A simple text field")
            },
            textStyle = MaterialTheme.typography.bodyMedium,
        )
    }
}