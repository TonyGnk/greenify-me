package com.example.greenifyme.data.material

import com.example.greenifyme.data.MaterialOptions
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString

fun MaterialOptions.toJSON() : String {
	return try {
		Json.encodeToString(this)
	} catch (e : Exception) {
		e.printStackTrace()
		""
	}
}

fun String.toMaterialOption() : MaterialOptions {
	return decodeFromString<MaterialOptions>(this)
}

