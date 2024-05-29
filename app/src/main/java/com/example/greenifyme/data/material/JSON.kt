package com.example.greenifyme.data.material

import androidx.room.TypeConverter
import com.example.greenifyme.data.OptionsType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString

class Converters {

    @TypeConverter
    fun fromOptionsType(optionsType: OptionsType): String {
        return Json.encodeToString(optionsType)
    }

    @TypeConverter
    fun toOptionsType(json: String): OptionsType {
        return decodeFromString<OptionsType>(json)
    }
}