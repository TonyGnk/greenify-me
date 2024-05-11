package com.example.greenifyme.data

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class DataObject

@Entity(tableName = "accounts_table")
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
    val id: Int = 0,
    val accountId: Int,
    val hasAdminViewed: Boolean,
    val createdAt: Long, //Epoch time format
) : DataObject()


@Entity(tableName = "tracked_material_table")
data class TrackedMaterial(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val track_id: Int,
        val material_id: Int,
        val quantity: Int
)

@Entity(tableName = "materials_table")
data class Material(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val type: Type,//enum
        val name: String,
        val total_quantity: Int
)

enum class Type {
    PAPER,
    PLASTIC,
    GLASS,
    METALS
}
