package com.example.greenifyme.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.greenifyme.data.material.MeasurementType

enum class DataObjectType {
    ACCOUNT,
    RECORD,
    MATERIAL
}

sealed class DataObject

@Entity(tableName = "accounts_table", indices = [Index(value = ["email"], unique = true)])
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean
) : DataObject()


@Entity(tableName = "records_table")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val recordId: Int = 0,
    val accountId: Int,
    val materialId: Int,
    val hasAdminViewed: Boolean,
    val createdAt: Long, //Epoch time format
) : DataObject()

@Entity(tableName = "materials_table")
data class Material(
    @PrimaryKey(autoGenerate = true)
    val materialId: Int = 0,
    val category: RecyclingCategory = RecyclingCategory.OTHER,
    val name: String,
    val options: String,
    val hasSubcategories: Boolean = true,
) : DataObject()

data class MaterialOption(
    val measurementType: MeasurementType,
    val pointsPerType: Int,
)

enum class RecyclingCategory(val description: String) {
    PAPER_CARDBOARD("Paper & Cardboard"),
    PLASTIC("Plastic"),
    METAL_CANS("Metal Cans"),
    ELECTRONICS("Electronics"),
    ORGANIC_WASTE("Organic Waste"),
    GLASS("Glass"),
    FABRIC("Fabric"),
    OTHER("Other"),
}