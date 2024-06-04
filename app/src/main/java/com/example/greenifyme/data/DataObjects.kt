package com.example.greenifyme.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.greenifyme.R
import kotlinx.serialization.Serializable

enum class DataObjectType {
    ACCOUNT,
    FORM,
    TRACK,
    MATERIAL
}

sealed class DataObject

@Entity(tableName = "accounts_table", indices = [Index(value = ["email"], unique = true)])
data class Account(
    @PrimaryKey(autoGenerate = true)
    val accountId: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val points: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val hasIntroViewed: Boolean = false
) : DataObject()


@Entity(
    tableName = "forms_table",
//    foreignKeys = [ForeignKey(
//        entity = Account::class,
//        parentColumns = ["accountId"],
//        childColumns = ["accountId"],
//        onDelete = ForeignKey.CASCADE
//    )],
//    indices = [Index(value = ["accountId"])]
)
data class Form(
    @PrimaryKey(autoGenerate = true)
    val formId: Int = 0,
    val accountId: Int = 0,
    val hasAdminViewed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
) : DataObject()


@Entity(
    tableName = "tracks_table",
//    foreignKeys = [
//        ForeignKey(
//            entity = Form::class,
//            parentColumns = ["formId"],
//            childColumns = ["formId"],
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = Material::class,
//            parentColumns = ["materialId"],
//            childColumns = ["materialId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ],
//    indices = [Index(value = ["formId"]), Index(value = ["materialId"])]
)
data class Track(
    @PrimaryKey(autoGenerate = true)
    val trackId: Int = 0,
    val formId: Int,
    val materialId: Int,
    val quantity: Float
) : DataObject()


@Entity(tableName = "materials_table")
data class Material(
    @PrimaryKey(autoGenerate = true)
    val materialId: Int = 0,
    val category: RecyclingCategory = RecyclingCategory.OTHER,
    val name: String = "",
    val type: OptionsType = Pieces(0f),
) : DataObject()

@Serializable
sealed class OptionsType {
    abstract val pointsPerPiece: Float
    abstract val pointsPerGram: Float
}

@Serializable
data class Pieces(
    override val pointsPerPiece: Float, override val pointsPerGram: Float = 0f
) : OptionsType()

@Serializable
data class Grams(
    override val pointsPerGram: Float, override val pointsPerPiece: Float = 0f
) : OptionsType()

@Serializable
data class Both(override val pointsPerPiece: Float, override val pointsPerGram: Float) :
    OptionsType()


enum class RecyclingCategory(val description: Int, val icon: Int) {
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