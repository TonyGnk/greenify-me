package com.example.greenifyme.data.relations


import androidx.room.Entity


@Entity(primaryKeys = ["recordId", "materialId"])
data class RecordMaterialCrossRef(
    val recordId: Int,
    val materialId: Int,
    val quantity: Int
)