package com.veroanggra.dessertlicious.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Dessert(
    val name: String,
    val flavor: String,
    val price: String,
    val desc: String,
    val photo: Int,
    val color: Color
)

data class MenuDessert(
    val key: Int,
    val imageMenu: Int,
    val ingredients: Int
)

data class MenuTopping(
    val key: Int,
    val iconTopping: Int,
    val imageTopping: Int,
    val price: Int
)
