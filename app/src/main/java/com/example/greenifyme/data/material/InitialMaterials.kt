package com.example.greenifyme.data.material

import com.example.greenifyme.data.Material
import com.example.greenifyme.data.MaterialOption
import com.example.greenifyme.data.RecyclingCategory

val initialMaterials = listOf(
    Material(
        1, RecyclingCategory.PLASTIC, "Bottle",
        listOf(
            MaterialOption(MeasurementType.KILOS, 2),
            MaterialOption(MeasurementType.PIECES, 1)
        ).toJSON()
    ),
    Material(
        2, RecyclingCategory.GLASS, "Bottle",
        listOf(
            MaterialOption(MeasurementType.KILOS, 5),
            MaterialOption(MeasurementType.PIECES, 3)
        ).toJSON()
    ),
    Material(
        3,
        category = RecyclingCategory.PAPER_CARDBOARD,
        name = "Newspaper",
        options = listOf(
            MaterialOption(MeasurementType.KILOS, 1),
            MaterialOption(MeasurementType.PIECES, 5)
        ).toJSON()
    ),
    Material(
        4,
        category = RecyclingCategory.PLASTIC,
        name = "Plastic Bag",
        options = listOf(
            MaterialOption(MeasurementType.PIECES, 5)
        ).toJSON()
    ),
    Material(
        5,
        category = RecyclingCategory.METAL_CANS,
        name = "Aluminum Can",
        options = listOf(
            MaterialOption(MeasurementType.PIECES, 1)
        ).toJSON()
    ),
    Material(
        6,
        category = RecyclingCategory.ELECTRONICS,
        name = "Smartphone",
        options = listOf(
            MaterialOption(MeasurementType.PIECES, 50)
        ).toJSON()
    ),
    Material(
        7,
        category = RecyclingCategory.ORGANIC_WASTE,
        name = "Food Scraps",
        options = listOf(
            MaterialOption(MeasurementType.KILOS, 2)
        ).toJSON()
    ),
    Material(
        7,
        category = RecyclingCategory.GLASS,
        name = "Jar",
        options = listOf(
            MaterialOption(MeasurementType.PIECES, 2)
        ).toJSON()
    ),
    Material(
        8,
        category = RecyclingCategory.FABRIC,
        name = "T-shirt",
        options = listOf(
            MaterialOption(MeasurementType.PIECES, 5)
        ).toJSON()
    ),
    Material(
        9,
        category = RecyclingCategory.OTHER,
        name = "Ceramics",
        options = listOf(
            MaterialOption(MeasurementType.KILOS, 3)
        ).toJSON()
    ),
    Material(
        10,
        category = RecyclingCategory.PAPER_CARDBOARD,
        name = "Cardboard Box",
        options = listOf(
            MaterialOption(MeasurementType.KILOS, 3)
        ).toJSON()
    ),
    Material(
        11,
        category = RecyclingCategory.PLASTIC,
        name = "Plastic Bottle Cap",
        options = listOf(
            MaterialOption(MeasurementType.PIECES, 1)
        ).toJSON()
    ),
    //More options will follow
    Material(
        12,
        category = RecyclingCategory.PLASTIC,
        name = "More options will be added soon",
        options = listOf(
            MaterialOption(MeasurementType.PIECES, 1)
        ).toJSON()
    )
)
