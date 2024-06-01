package com.example.greenifyme.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.data.Track

data class FormWithTracks(
    @Embedded val form: Form,
    @Relation(
        parentColumn = "formId",
        entityColumn = "formId"
    )
    val tracks: List<Track>
)

data class MaterialWithTracks(
    @Embedded val material: Material,
    @Relation(
        parentColumn = "materialId",
        entityColumn = "materialId"
    )
    val tracks: List<Track>
)

data class CategoryQuantitySum(
    val category: RecyclingCategory,
    val totalQuantity: Int
)


data class WinnerItem(
    val name: String,
    val totalPoints: Int
)