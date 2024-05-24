package com.example.greenifyme.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.greenifyme.R
import kotlinx.serialization.Serializable

enum class DataObjectType {
	ACCOUNT,
	FORM,
	MATERIAL
}

sealed class DataObject



@Entity(tableName = "accounts_table", indices = [Index(value = ["email"], unique = true)])
data class Account(
	@PrimaryKey(autoGenerate = true)
	val id : Int = 0,
	val name : String,
	val email : String,
	val password : String,
	val isAdmin : Boolean
) : DataObject()



@Entity(tableName = "forms_table")
data class Form(
	@PrimaryKey(autoGenerate = true)
	val formId : Int = 0,
	val accountId : Int,
	val hasAdminViewed : Boolean = false,
	val createdAt : Long = System.currentTimeMillis(),
) : DataObject()



@Entity(tableName = "materials_table")
data class Material(
	@PrimaryKey(autoGenerate = true)
	val materialId : Int = 0,
	val category : RecyclingCategory = RecyclingCategory.OTHER,
	val name : String,
	val options : String,
	val hasSubcategories : Boolean = true,
) : DataObject()



@Serializable
data class MaterialOptions(
	val type : OptionsType = OptionsType.PIECES,
	val pointsPerPiece : Int? = null,
	val pointsPerGram : Int? = null
)

enum class OptionsType { PIECES, WEIGHT, PIECES_WEIGHT }
enum class RecyclingCategory(val description : Int, val icon : Int) {
	PAPER_CARDBOARD(R.string.recycling_category_paper, R.drawable.box_open),
	PLASTIC(R.string.recycling_category_plastic, R.drawable.bin_bottles),
	METAL_CANS(R.string.recycling_category_metal_cans, R.drawable.can_food),
	ELECTRONICS(R.string.recycling_category_electronics, R.drawable.washer),
	ORGANIC_WASTE(R.string.recycling_category_organic, R.drawable.apple_core),
	GLASS(R.string.recycling_category_glass, R.drawable.glass),
	FABRIC(R.string.recycling_category_fabric, R.drawable.shirt_long_sleeve),
	HAZARDOUS_WASTE(R.string.recycling_category_hazardous, R.drawable.shield_exclamation),
	OTHER(R.string.recycling_category_other, R.drawable.seal_question)
}

