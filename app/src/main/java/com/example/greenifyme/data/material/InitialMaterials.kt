package com.example.greenifyme.data.material

import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.data.RecyclingCategory

val initialMaterials = listOf(
    Material(
        materialId = 1, category = RecyclingCategory.GLASS, name = "Glass Bottle",
        type = Pieces(7),
    ),
    Material(
        materialId = 2, category = RecyclingCategory.FABRIC, name = "Cotton Shirt",
        type = Grams(27),
    ),
    Material(
        materialId = 3, category = RecyclingCategory.ORGANIC_WASTE, name = "Apple",
        type = Pieces(23),
    ),
    Material(
        materialId = 4, category = RecyclingCategory.PAPER_CARDBOARD, name = "Cardboard Box",
        type = Grams(10),
    ),
    Material(
        materialId = 5, category = RecyclingCategory.PLASTIC, name = "Plastic Bottle",
        type = Pieces(5)
    ),
    Material(
        materialId = 6, category = RecyclingCategory.METAL_CANS, name = "Aluminum Can",
        type = Pieces(8)
    ),
    Material(
        materialId = 7, category = RecyclingCategory.ELECTRONICS, name = "Old Phone",
        type = Grams(50)
    ),
    Material(
        materialId = 8, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Used Battery",
        type = Pieces(12)
    ),
    Material(
        materialId = 9, category = RecyclingCategory.OTHER, name = "Mixed Materials",
        type = Grams(3)
    ),
    Material(
        materialId = 10, category = RecyclingCategory.ORGANIC_WASTE, name = "Banana Peel",
        type = Both(20, 5)
    ),
    Material(
        materialId = 11, category = RecyclingCategory.PAPER_CARDBOARD, name = "Newspaper",
        type = Grams(6)
    ),
    Material(
        materialId = 12, category = RecyclingCategory.PLASTIC, name = "Plastic Bag",
        type = Pieces(4)
    ),
    Material(
        materialId = 13, category = RecyclingCategory.METAL_CANS, name = "Tin Can",
        type = Pieces(9)
    ),
    Material(
        materialId = 14, category = RecyclingCategory.FABRIC, name = "Wool Sweater",
        type = Grams(30)
    ),
    Material(
        materialId = 15, category = RecyclingCategory.ELECTRONICS, name = "Laptop",
        type = Grams(60)
    ),
    Material(
        materialId = 16, category = RecyclingCategory.GLASS, name = "Glass Jar",
        type = Pieces(6)
    ),
    Material(
        materialId = 17, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Fluorescent Bulb",
        type = Pieces(15)
    ),
    Material(
        materialId = 18, category = RecyclingCategory.OTHER, name = "Broken Ceramics",
        type = Grams(2)
    ),
    Material(
        materialId = 19, category = RecyclingCategory.ORGANIC_WASTE, name = "Orange Peel",
        type = Both(18, 7)
    ),
    Material(
        materialId = 20, category = RecyclingCategory.PAPER_CARDBOARD, name = "Paper Sheet",
        type = Pieces(2)
    ),
    Material(
        materialId = 21, category = RecyclingCategory.GLASS, name = "Wine Bottle",
        type = Pieces(10)
    ),
    Material(
        materialId = 22, category = RecyclingCategory.FABRIC, name = "Denim Jeans",
        type = Grams(25)
    ),
    Material(
        materialId = 23, category = RecyclingCategory.ORGANIC_WASTE, name = "Carrot Peel",
        type = Both(15, 8)
    ),
    Material(
        materialId = 24, category = RecyclingCategory.PAPER_CARDBOARD, name = "Magazine",
        type = Grams(8)
    ),
    Material(
        materialId = 25, category = RecyclingCategory.PLASTIC, name = "Plastic Straw",
        type = Pieces(3)
    ),
    Material(
        materialId = 26, category = RecyclingCategory.METAL_CANS, name = "Steel Can",
        type = Pieces(11)
    ),
    Material(
        materialId = 27, category = RecyclingCategory.ELECTRONICS, name = "Old Tablet",
        type = Grams(55)
    ),
    Material(
        materialId = 28, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Paint Can",
        type = Pieces(20)
    ),
    Material(
        materialId = 29, category = RecyclingCategory.OTHER, name = "Rubber Tires",
        type = Grams(4)
    ),
    Material(
        materialId = 30, category = RecyclingCategory.ORGANIC_WASTE, name = "Potato Peel",
        type = Both(13, 6)
    ),
    Material(
        materialId = 31, category = RecyclingCategory.PAPER_CARDBOARD, name = "Office Paper",
        type = Grams(9)
    ),
    Material(
        materialId = 32, category = RecyclingCategory.PLASTIC, name = "Plastic Cup",
        type = Pieces(6)
    ),
    Material(
        materialId = 33, category = RecyclingCategory.METAL_CANS, name = "Copper Can",
        type = Pieces(14)
    ),
    Material(
        materialId = 34, category = RecyclingCategory.FABRIC, name = "Silk Scarf",
        type = Grams(35)
    ),
    Material(
        materialId = 35, category = RecyclingCategory.ELECTRONICS, name = "Old Camera",
        type = Grams(45)
    ),
    Material(
        materialId = 36, category = RecyclingCategory.GLASS, name = "Glass Cup",
        type = Pieces(9)
    ),
    Material(
        materialId = 37, category = RecyclingCategory.FABRIC, name = "Wool Blanket",
        type = Grams(28)
    ),
    Material(
        materialId = 38, category = RecyclingCategory.ORGANIC_WASTE, name = "Tomato Peel",
        type = Both(12, 5)
    ),
    Material(
        materialId = 39, category = RecyclingCategory.PAPER_CARDBOARD, name = "Paper Towel",
        type = Grams(7)
    ),
    Material(
        materialId = 40, category = RecyclingCategory.PLASTIC, name = "Plastic Fork",
        type = Pieces(4)
    ),
    Material(
        materialId = 41, category = RecyclingCategory.METAL_CANS, name = "Brass Can",
        type = Pieces(10)
    ),
    Material(
        materialId = 42, category = RecyclingCategory.ELECTRONICS, name = "Old Radio",
        type = Grams(48)
    ),
    Material(
        materialId = 43, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Thermometer",
        type = Pieces(22)
    ),
    Material(
        materialId = 44, category = RecyclingCategory.OTHER, name = "Scrap Metal",
        type = Grams(6)
    ),
    Material(
        materialId = 45, category = RecyclingCategory.ORGANIC_WASTE, name = "Cucumber Peel",
        type = Both(14, 7)
    ),
    Material(
        materialId = 46, category = RecyclingCategory.PAPER_CARDBOARD, name = "Envelope",
        type = Grams(6)
    ),
    Material(
        materialId = 47, category = RecyclingCategory.PLASTIC, name = "Plastic Lid",
        type = Pieces(5)
    ),
    Material(
        materialId = 48, category = RecyclingCategory.METAL_CANS, name = "Zinc Can",
        type = Pieces(12)
    ),
    Material(
        materialId = 49, category = RecyclingCategory.FABRIC, name = "Polyester Jacket",
        type = Grams(20)
    ),
    Material(
        materialId = 50, category = RecyclingCategory.ELECTRONICS, name = "Old Monitor",
        type = Grams(52)
    )
)