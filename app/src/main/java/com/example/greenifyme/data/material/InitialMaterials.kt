package com.example.greenifyme.data.material

import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.data.RecyclingCategory


val initialMaterials = listOf(
    Material(
        materialId = 1, category = RecyclingCategory.GLASS, name = "Glass Bottle",
        type = Pieces(20f),
    ),
    Material(
        materialId = 2, category = RecyclingCategory.FABRIC, name = "Cotton Shirt",
        type = Grams(0.3f),// a medium-sized t-shirt made from a standard cotton fabric weighs around 130–155 grams(google).
    ),
    Material(
        materialId = 3, category = RecyclingCategory.ORGANIC_WASTE, name = "Apple",
        type = Pieces(5f),
    ),
    Material(
        materialId = 4, category = RecyclingCategory.PAPER_CARDBOARD, name = "Cardboard Box",
        type = Grams(0.2f),//On average, a large empty cardboard box might weigh anywhere from 0.5 to 2 kilograms
    ),
    Material(
        materialId = 5, category = RecyclingCategory.PLASTIC, name = "Plastic Bottle",
        type = Pieces(5f)
    ),
    Material(
        materialId = 6, category = RecyclingCategory.METAL_CANS, name = "Aluminum Can",
        type = Pieces(10f)
    ),
    Material(
        materialId = 7, category = RecyclingCategory.ELECTRONICS, name = "Old Phone",
        type = Grams(1.2f)  // How much does a Nokia 3310 weight in grams At 136 grams each
    ),
    Material(
        materialId = 8, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Used Battery",
        type = Pieces(15f)
    ),
    Material(
        materialId = 9, category = RecyclingCategory.OTHER, name = "Mixed Materials",
        type = Grams(0.03f)
    ),
    Material(
        materialId = 10, category = RecyclingCategory.ORGANIC_WASTE, name = "Banana Peel",
        type = Both(5f, 0.2f)//183 grams unpeeled, 116 grams, THE PEEL ~80g
    ),
    Material(
        materialId = 11, category = RecyclingCategory.PAPER_CARDBOARD, name = "Newspaper",
        type = Grams(0.6f) // avg weight of a newspaper 28 grams
    ),
    Material(
        materialId = 12, category = RecyclingCategory.PLASTIC, name = "Plastic Bag",
        type = Pieces(3f)
    ),
    Material(
        materialId = 13, category = RecyclingCategory.METAL_CANS, name = "Tin Can",
        type = Pieces(10f)
    ),
    Material(
        materialId = 14, category = RecyclingCategory.FABRIC, name = "Wool Sweater",
        type = Grams(0.15f)// around 350~700g each
    ),
    Material(
        materialId = 15, category = RecyclingCategory.ELECTRONICS, name = "Laptop",
        type = Grams(1f)
    ),
    Material(
        materialId = 16, category = RecyclingCategory.GLASS, name = "Glass Jar",
        type = Pieces(7f)
    ),
    Material(
        materialId = 17, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Fluorescent Bulb",
        type = Pieces(15f)
    ),
    Material(
        materialId = 18, category = RecyclingCategory.OTHER, name = "Broken Ceramics",
        type = Grams(0.3f)
    ),
    Material(
        materialId = 19, category = RecyclingCategory.ORGANIC_WASTE, name = "Orange Peel",
        type = Both(5f, 0.5f)
    ),
    Material(
        materialId = 20, category = RecyclingCategory.PAPER_CARDBOARD, name = "Paper Sheet",
        type = Pieces(0.2f)
    ),
    Material(
        materialId = 21, category = RecyclingCategory.GLASS, name = "Wine Bottle",
        type = Pieces(10f)
    ),
    Material(
        materialId = 22, category = RecyclingCategory.FABRIC, name = "Denim Jeans",
        type = Grams(0.07f) //A pair of man's denim jeans will be somewhere between 1.5 and 2 kg
    ),
    Material(
        materialId = 23, category = RecyclingCategory.ORGANIC_WASTE, name = "Carrot Peel",
        type = Both(2f, 0.3f)
    ),
    Material(
        materialId = 24, category = RecyclingCategory.PAPER_CARDBOARD, name = "Magazine",
        type = Grams(0.6f)
    ),
    Material(
        materialId = 25, category = RecyclingCategory.PLASTIC, name = "Plastic Straw",
        type = Pieces(2f)
    ),
    Material(
        materialId = 26, category = RecyclingCategory.METAL_CANS, name = "Steel Can",
        type = Pieces(12f)
    ),
    Material(
        materialId = 27, category = RecyclingCategory.ELECTRONICS, name = "Old Tablet",
        type = Grams(1.2f)
    ),
    Material(
        materialId = 28, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Paint Can",
        type = Pieces(15f)
    ),
    Material(
        materialId = 29, category = RecyclingCategory.OTHER, name = "Rubber Tires",
        type = Grams(0.02f) //Tyres vary in size, type and other parameters, and that's why
        // the weight of a tyre can be anything between 6.5 and 80 kg
    ),
    Material(
        materialId = 30, category = RecyclingCategory.ORGANIC_WASTE, name = "Potato Peel",
        type = Both(5f, 0.3f)
    ),
    Material(
        materialId = 31, category = RecyclingCategory.PAPER_CARDBOARD, name = "Office Paper",
        type = Grams(0.6f)
    ),
    Material(
        materialId = 32, category = RecyclingCategory.PLASTIC, name = "Plastic Cup",
        type = Pieces(3f)
    ),
    Material(
        materialId = 33, category = RecyclingCategory.METAL_CANS, name = "Copper Can",
        type = Pieces(15f)
    ),
    Material(
        materialId = 34, category = RecyclingCategory.FABRIC, name = "Silk Scarf", //A silk scarf is usually produced
        // in a weight of 12 - 16 momme (16 x 4.34g sqm = 69 grams per square metre) so let's  say our scarf is 2sqrm
        type = Grams(1f)
    ),
    Material(
        materialId = 35, category = RecyclingCategory.ELECTRONICS, name = "Old Camera",
        type = Grams(0.8f)
    ),
    Material(
        materialId = 36, category = RecyclingCategory.GLASS, name = "Glass Cup",
        type = Pieces(7f)
    ),
    Material(
        materialId = 37, category = RecyclingCategory.FABRIC, name = "Wool Blanket",
        type = Grams(0.07f) // around 2.154564
    ),
    Material(
        materialId = 38, category = RecyclingCategory.ORGANIC_WASTE, name = "Tomato Peel",
        type = Both(3f, 0.3f)
    ),
    Material(
        materialId = 39, category = RecyclingCategory.PAPER_CARDBOARD, name = "Paper Towel",
        type = Grams(0.2f)
    ),
    Material(
        materialId = 40, category = RecyclingCategory.PLASTIC, name = "Plastic Fork",
        type = Pieces(0.5f)
    ),
    Material(
        materialId = 41, category = RecyclingCategory.METAL_CANS, name = "Brass Can",
        type = Pieces(10f)
    ),
    Material(
        materialId = 42, category = RecyclingCategory.ELECTRONICS, name = "Old Radio",
        type = Grams(0.3f)
    ),
    Material(
        materialId = 43, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Thermometer",
        type = Pieces(20f)
    ),
    Material(
        materialId = 44, category = RecyclingCategory.OTHER, name = "Scrap Metal",
        type = Grams(0.03f)
    ),
    Material(
        materialId = 45, category = RecyclingCategory.ORGANIC_WASTE, name = "Cucumber Peel",
        type = Both(3f, 0.2f)
    ),
    Material(
        materialId = 46, category = RecyclingCategory.PAPER_CARDBOARD, name = "Envelope",
        type = Grams(0.2f)
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
        type = Grams(0.06f)
    ),
    Material(
        materialId = 50, category = RecyclingCategory.ELECTRONICS, name = "Old Monitor",
        type = Grams(0.8f)
    )
)