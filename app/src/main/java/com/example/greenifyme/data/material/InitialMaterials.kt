package com.example.greenifyme.data.material

import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.data.RecyclingCategory

val initialMaterials = listOf(
    Material(
        materialId = 1, category = RecyclingCategory.GLASS, name = "Glass Bottle",
        type = Pieces(7f),
    ),
    Material(
        materialId = 2, category = RecyclingCategory.FABRIC, name = "Cotton Shirt",
        type = Grams(27f),
    ),
    Material(
        materialId = 3, category = RecyclingCategory.ORGANIC_WASTE, name = "Apple",
        type = Pieces(23f),
    ),
    Material(
        materialId = 4, category = RecyclingCategory.PAPER_CARDBOARD, name = "Cardboard Box",
        type = Grams(10f),
    ),
    Material(
        materialId = 5, category = RecyclingCategory.PLASTIC, name = "Plastic Bottle",
        type = Pieces(5f)
    ),
    Material(
        materialId = 6, category = RecyclingCategory.METAL_CANS, name = "Aluminum Can",
        type = Pieces(8f)
    ),
    Material(
        materialId = 7, category = RecyclingCategory.ELECTRONICS, name = "Old Phone",
        type = Grams(50f)
    ),
    Material(
        materialId = 8, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Used Battery",
        type = Pieces(12f)
    ),
    Material(
        materialId = 9, category = RecyclingCategory.OTHER, name = "Mixed Materials",
        type = Grams(3f)
    ),
    Material(
        materialId = 10, category = RecyclingCategory.ORGANIC_WASTE, name = "Banana Peel",
        type = Both(20f, 5f)
    ),
    Material(
        materialId = 11, category = RecyclingCategory.PAPER_CARDBOARD, name = "Newspaper",
        type = Grams(6f)
    ),
    Material(
        materialId = 12, category = RecyclingCategory.PLASTIC, name = "Plastic Bag",
        type = Pieces(4f)
    ),
    Material(
        materialId = 13, category = RecyclingCategory.METAL_CANS, name = "Tin Can",
        type = Pieces(9f)
    ),
    Material(
        materialId = 14, category = RecyclingCategory.FABRIC, name = "Wool Sweater",
        type = Grams(30f)
    ),
    Material(
        materialId = 15, category = RecyclingCategory.ELECTRONICS, name = "Laptop",
        type = Grams(60f)
    ),
    Material(
        materialId = 16, category = RecyclingCategory.GLASS, name = "Glass Jar",
        type = Pieces(6f)
    ),
    Material(
        materialId = 17, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Fluorescent Bulb",
        type = Pieces(15f)
    ),
    Material(
        materialId = 18, category = RecyclingCategory.OTHER, name = "Broken Ceramics",
        type = Grams(2f)
    ),
    Material(
        materialId = 19, category = RecyclingCategory.ORGANIC_WASTE, name = "Orange Peel",
        type = Both(18f, 7f)
    ),
    Material(
        materialId = 20, category = RecyclingCategory.PAPER_CARDBOARD, name = "Paper Sheet",
        type = Pieces(2f)
    ),
    Material(
        materialId = 21, category = RecyclingCategory.GLASS, name = "Wine Bottle",
        type = Pieces(10f)
    ),
    Material(
        materialId = 22, category = RecyclingCategory.FABRIC, name = "Denim Jeans",
        type = Grams(25f)
    ),
    Material(
        materialId = 23, category = RecyclingCategory.ORGANIC_WASTE, name = "Carrot Peel",
        type = Both(15f, 8f)
    ),
    Material(
        materialId = 24, category = RecyclingCategory.PAPER_CARDBOARD, name = "Magazine",
        type = Grams(8f)
    ),
    Material(
        materialId = 25, category = RecyclingCategory.PLASTIC, name = "Plastic Straw",
        type = Pieces(3f)
    ),
    Material(
        materialId = 26, category = RecyclingCategory.METAL_CANS, name = "Steel Can",
        type = Pieces(11f)
    ),
    Material(
        materialId = 27, category = RecyclingCategory.ELECTRONICS, name = "Old Tablet",
        type = Grams(55f)
    ),
    Material(
        materialId = 28, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Paint Can",
        type = Pieces(20f)
    ),
    Material(
        materialId = 29, category = RecyclingCategory.OTHER, name = "Rubber Tires",
        type = Grams(4f)
    ),
    Material(
        materialId = 30, category = RecyclingCategory.ORGANIC_WASTE, name = "Potato Peel",
        type = Both(13f, 6f)
    ),
    Material(
        materialId = 31, category = RecyclingCategory.PAPER_CARDBOARD, name = "Office Paper",
        type = Grams(9f)
    ),
    Material(
        materialId = 32, category = RecyclingCategory.PLASTIC, name = "Plastic Cup",
        type = Pieces(6f)
    ),
    Material(
        materialId = 33, category = RecyclingCategory.METAL_CANS, name = "Copper Can",
        type = Pieces(14f)
    ),
    Material(
        materialId = 34, category = RecyclingCategory.FABRIC, name = "Silk Scarf",
        type = Grams(35f)
    ),
    Material(
        materialId = 35, category = RecyclingCategory.ELECTRONICS, name = "Old Camera",
        type = Grams(45f)
    ),
    Material(
        materialId = 36, category = RecyclingCategory.GLASS, name = "Glass Cup",
        type = Pieces(9f)
    ),
    Material(
        materialId = 37, category = RecyclingCategory.FABRIC, name = "Wool Blanket",
        type = Grams(28f)
    ),
    Material(
        materialId = 38, category = RecyclingCategory.ORGANIC_WASTE, name = "Tomato Peel",
        type = Both(12f, 5f)
    ),
    Material(
        materialId = 39, category = RecyclingCategory.PAPER_CARDBOARD, name = "Paper Towel",
        type = Grams(7f)
    ),
    Material(
        materialId = 40, category = RecyclingCategory.PLASTIC, name = "Plastic Fork",
        type = Pieces(4f)
    ),
    Material(
        materialId = 41, category = RecyclingCategory.METAL_CANS, name = "Brass Can",
        type = Pieces(10f)
    ),
    Material(
        materialId = 42, category = RecyclingCategory.ELECTRONICS, name = "Old Radio",
        type = Grams(48f)
    ),
    Material(
        materialId = 43, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Thermometer",
        type = Pieces(22f)
    ),
    Material(
        materialId = 44, category = RecyclingCategory.OTHER, name = "Scrap Metal",
        type = Grams(6f)
    ),
    Material(
        materialId = 45, category = RecyclingCategory.ORGANIC_WASTE, name = "Cucumber Peel",
        type = Both(14f, 7f)
    ),
    Material(
        materialId = 46, category = RecyclingCategory.PAPER_CARDBOARD, name = "Envelope",
        type = Grams(6f)
    ),
    Material(
        materialId = 47, category = RecyclingCategory.PLASTIC, name = "Plastic Lid",
        type = Pieces(5f)
    ),
    Material(
        materialId = 48, category = RecyclingCategory.METAL_CANS, name = "Zinc Can",
        type = Pieces(12f)
    ),
    Material(
        materialId = 49, category = RecyclingCategory.FABRIC, name = "Polyester Jacket",
        type = Grams(20f)
    ),
    Material(
        materialId = 50, category = RecyclingCategory.ELECTRONICS, name = "Old Monitor",
        type = Grams(52f)
    )
)