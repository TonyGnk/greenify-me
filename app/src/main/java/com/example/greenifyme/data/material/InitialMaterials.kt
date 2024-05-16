package com.example.greenifyme.data.material

import com.example.greenifyme.data.Material
import com.example.greenifyme.data.MaterialOptions
import com.example.greenifyme.data.OptionsType
import com.example.greenifyme.data.RecyclingCategory

val initialMaterials = listOf(
	Material(
		materialId = 1, category = RecyclingCategory.GLASS, name = "Glass Bottle",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 7,
		).toJSON()
	),
	Material(
		materialId = 2, category = RecyclingCategory.FABRIC, name = "Cotton Shirt",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 27,
		).toJSON()
	),
	Material(
		materialId = 3, category = RecyclingCategory.ORGANIC_WASTE, name = "Apple",
		options = MaterialOptions(
			type = OptionsType.PIECES_WEIGHT,
			pointsPerPiece = 23,
			pointsPerGram = 14,
		).toJSON()
	),
	Material(
		materialId = 4, category = RecyclingCategory.PAPER_CARDBOARD, name = "Cardboard Box",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 10,
		).toJSON()
	),
	Material(
		materialId = 5, category = RecyclingCategory.PLASTIC, name = "Plastic Bottle",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 5,
		).toJSON()
	),
	Material(
		materialId = 6, category = RecyclingCategory.METAL_CANS, name = "Aluminum Can",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 8,
		).toJSON()
	),
	Material(
		materialId = 7, category = RecyclingCategory.ELECTRONICS, name = "Old Phone",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 50,
		).toJSON()
	),
	Material(
		materialId = 8, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Used Battery",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 12,
		).toJSON()
	),
	Material(
		materialId = 9, category = RecyclingCategory.OTHER, name = "Mixed Materials",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 3,
		).toJSON()
	),
	Material(
		materialId = 10, category = RecyclingCategory.ORGANIC_WASTE, name = "Banana Peel",
		options = MaterialOptions(
			type = OptionsType.PIECES_WEIGHT,
			pointsPerPiece = 20,
			pointsPerGram = 5,
		).toJSON()
	),
	Material(
		materialId = 11, category = RecyclingCategory.PAPER_CARDBOARD, name = "Newspaper",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 6,
		).toJSON()
	),
	Material(
		materialId = 12, category = RecyclingCategory.PLASTIC, name = "Plastic Bag",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 4,
		).toJSON()
	),
	Material(
		materialId = 13, category = RecyclingCategory.METAL_CANS, name = "Tin Can",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 9,
		).toJSON()
	),
	Material(
		materialId = 14, category = RecyclingCategory.FABRIC, name = "Wool Sweater",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 30,
		).toJSON()
	),
	Material(
		materialId = 15, category = RecyclingCategory.ELECTRONICS, name = "Laptop",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 60,
		).toJSON()
	),
	Material(
		materialId = 16, category = RecyclingCategory.GLASS, name = "Glass Jar",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 6,
		).toJSON()
	),
	Material(
		materialId = 17, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Fluorescent Bulb",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 15,
		).toJSON()
	),
	Material(
		materialId = 18, category = RecyclingCategory.OTHER, name = "Broken Ceramics",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 2,
		).toJSON()
	),
	Material(
		materialId = 19, category = RecyclingCategory.ORGANIC_WASTE, name = "Orange Peel",
		options = MaterialOptions(
			type = OptionsType.PIECES_WEIGHT,
			pointsPerPiece = 18,
			pointsPerGram = 7,
		).toJSON()
	),
	Material(
		materialId = 20, category = RecyclingCategory.PAPER_CARDBOARD, name = "Paper Sheet",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 2,
		).toJSON()
	),
	Material(
		materialId = 21, category = RecyclingCategory.GLASS, name = "Wine Bottle",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 10,
		).toJSON()
	),
	Material(
		materialId = 22, category = RecyclingCategory.FABRIC, name = "Denim Jeans",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 25,
		).toJSON()
	),
	Material(
		materialId = 23, category = RecyclingCategory.ORGANIC_WASTE, name = "Carrot Peel",
		options = MaterialOptions(
			type = OptionsType.PIECES_WEIGHT,
			pointsPerPiece = 15,
			pointsPerGram = 8,
		).toJSON()
	),
	Material(
		materialId = 24, category = RecyclingCategory.PAPER_CARDBOARD, name = "Magazine",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 8,
		).toJSON()
	),
	Material(
		materialId = 25, category = RecyclingCategory.PLASTIC, name = "Plastic Straw",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 3,
		).toJSON()
	),
	Material(
		materialId = 26, category = RecyclingCategory.METAL_CANS, name = "Steel Can",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 11,
		).toJSON()
	),
	Material(
		materialId = 27, category = RecyclingCategory.ELECTRONICS, name = "Old Tablet",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 55,
		).toJSON()
	),
	Material(
		materialId = 28, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Paint Can",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 20,
		).toJSON()
	),
	Material(
		materialId = 29, category = RecyclingCategory.OTHER, name = "Rubber Tires",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 4,
		).toJSON()
	),
	Material(
		materialId = 30, category = RecyclingCategory.ORGANIC_WASTE, name = "Potato Peel",
		options = MaterialOptions(
			type = OptionsType.PIECES_WEIGHT,
			pointsPerPiece = 13,
			pointsPerGram = 6,
		).toJSON()
	),
	Material(
		materialId = 31, category = RecyclingCategory.PAPER_CARDBOARD, name = "Office Paper",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 9,
		).toJSON()
	),
	Material(
		materialId = 32, category = RecyclingCategory.PLASTIC, name = "Plastic Cup",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 6,
		).toJSON()
	),
	Material(
		materialId = 33, category = RecyclingCategory.METAL_CANS, name = "Copper Can",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 14,
		).toJSON()
	),
	Material(
		materialId = 34, category = RecyclingCategory.FABRIC, name = "Silk Scarf",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 35,
		).toJSON()
	),
	Material(
		materialId = 35, category = RecyclingCategory.ELECTRONICS, name = "Old Camera",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 45,
		).toJSON()
	),
	Material(
		materialId = 36, category = RecyclingCategory.GLASS, name = "Glass Cup",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 9,
		).toJSON()
	),
	Material(
		materialId = 37, category = RecyclingCategory.FABRIC, name = "Wool Blanket",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 28,
		).toJSON()
	),
	Material(
		materialId = 38, category = RecyclingCategory.ORGANIC_WASTE, name = "Tomato Peel",
		options = MaterialOptions(
			type = OptionsType.PIECES_WEIGHT,
			pointsPerPiece = 12,
			pointsPerGram = 5,
		).toJSON()
	),
	Material(
		materialId = 39, category = RecyclingCategory.PAPER_CARDBOARD, name = "Paper Towel",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 7,
		).toJSON()
	),
	Material(
		materialId = 40, category = RecyclingCategory.PLASTIC, name = "Plastic Fork",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 4,
		).toJSON()
	),
	Material(
		materialId = 41, category = RecyclingCategory.METAL_CANS, name = "Brass Can",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 10,
		).toJSON()
	),
	Material(
		materialId = 42, category = RecyclingCategory.ELECTRONICS, name = "Old Radio",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 48,
		).toJSON()
	),
	Material(
		materialId = 43, category = RecyclingCategory.HAZARDOUS_WASTE, name = "Mercury Thermometer",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 22,
		).toJSON()
	),
	Material(
		materialId = 44, category = RecyclingCategory.OTHER, name = "Mixed Scrap Metal",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 6,
		).toJSON()
	),
	Material(
		materialId = 45, category = RecyclingCategory.ORGANIC_WASTE, name = "Cucumber Peel",
		options = MaterialOptions(
			type = OptionsType.PIECES_WEIGHT,
			pointsPerPiece = 14,
			pointsPerGram = 7,
		).toJSON()
	),
	Material(
		materialId = 46, category = RecyclingCategory.PAPER_CARDBOARD, name = "Envelope",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 6,
		).toJSON()
	),
	Material(
		materialId = 47, category = RecyclingCategory.PLASTIC, name = "Plastic Lid",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 5,
		).toJSON()
	),
	Material(
		materialId = 48, category = RecyclingCategory.METAL_CANS, name = "Zinc Can",
		options = MaterialOptions(
			type = OptionsType.PIECES,
			pointsPerPiece = 12,
		).toJSON()
	),
	Material(
		materialId = 49, category = RecyclingCategory.FABRIC, name = "Polyester Jacket",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 20,
		).toJSON()
	),
	Material(
		materialId = 50, category = RecyclingCategory.ELECTRONICS, name = "Old Monitor",
		options = MaterialOptions(
			type = OptionsType.WEIGHT,
			pointsPerGram = 52,
		).toJSON()
	)
)



