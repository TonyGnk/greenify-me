package com.example.greenifyme.data.material

import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Type


fun populateMaterial() = listOf(
    Material(0, Type.PLASTIC, "Bottle", 2),
    Material(1,Type.GLASS,"Bottle", 5),
    Material(2, Type.METALS,"Can", 1),
    Material(3,Type.PAPER,"Carton", 4)
)

