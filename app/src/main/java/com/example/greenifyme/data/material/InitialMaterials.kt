package com.example.greenifyme.data.material

import com.example.greenifyme.data.Material
import com.example.greenifyme.data.MaterialOption
import com.example.greenifyme.data.RecyclingCategory

val initialMaterials = listOf(
    Material(
        id = 0, category = RecyclingCategory.PLASTIC, name = "Bottle",
        options = listOf(
            MaterialOption(MeasurementType.KILOS, 2),
            MaterialOption(MeasurementType.PIECES, 1)
        ).toJSON()
    ),
    //Convert Bottle glass
    Material(
        id = 1, category = RecyclingCategory.GLASS, name = "Bottle",
        options = listOf(
            MaterialOption(MeasurementType.KILOS, 5),
            MaterialOption(MeasurementType.PIECES, 3)
        ).toJSON()
    ),
)