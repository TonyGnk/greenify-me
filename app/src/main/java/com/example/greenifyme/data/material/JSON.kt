package com.example.greenifyme.data.material

import com.example.greenifyme.data.MaterialOption
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString

fun List<MaterialOption>.toJSON(): String {
    return try {
        Json.encodeToString(this)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}


fun String.toMaterialOptions(): List<MaterialOption>? {
    return try {
        decodeFromString<List<MaterialOption>>(this)
    } catch (e: Exception) {
        null
    }
}

enum class MeasurementType {
    GRAMS,
    KILOS,
    ML,
    LITERS,
    PIECES
}
