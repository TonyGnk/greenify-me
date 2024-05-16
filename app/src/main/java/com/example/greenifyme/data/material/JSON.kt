package com.example.greenifyme.data.material

import com.example.greenifyme.data.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
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

