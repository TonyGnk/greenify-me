package com.example.greenifyme.data.material

import com.example.greenifyme.data.MaterialOption
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString

fun List<MaterialOption>.toJSON(): String {
    return Json.encodeToString(this)
}


fun deconvertFromString(optionString: String): List<MaterialOption>? {
    return try {
        decodeFromString<List<MaterialOption>>(optionString)
    } catch (e: Exception) {
        null
    }
}

//Τρόπος Μέτρησης: Γραμμάρια, Κιλά, μλ, λίτρα, τεμάχια
enum class MeasurementType {
    GRAMS,
    KILOS,
    ML,
    LITERS,
    PIECES
}
