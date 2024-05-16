package com.example.greenifyme.data.relations

import androidx.room.Entity

@Entity(
	tableName = "form_material_cross_ref",
	primaryKeys = ["formId", "materialId"]
)
data class FormMaterialCrossRef(
	val formId : Int,
	val materialId : Int,
	val quantity : Int
)