package com.veroanggra.dessertlicious.data

import com.veroanggra.dessertlicious.R
import com.veroanggra.dessertlicious.model.Dessert
import com.veroanggra.dessertlicious.model.MenuDessert
import com.veroanggra.dessertlicious.ui.theme.*

object DessertData {
    val desserts = listOf(
        Dessert(
            "Black Jack",
            "Chocolate",
            "$2",
            "A donut is a round, deep fried cake, usually with a hole in the center. Your favorite breakfast might be a chocolate donut and a cup of coffee.",
            R.drawable.donut_1,
            Pink1
        ),
        Dessert(
            "Crunchy",
            "Chocolate",
            "$5",
            "A donut is a round, deep fried cake, usually with a hole in the center. Your favorite breakfast might be a chocolate donut and a cup of coffee.",
            R.drawable.donut_2,
            Blue1
        ),
        Dessert(
            "Choco Hustle",
            "Chocolate",
            "$3",
            "A donut is a round, deep fried cake, usually with a hole in the center. Your favorite breakfast might be a chocolate donut and a cup of coffee.",
            R.drawable.donut_3,
            Yellow1
        ),
        Dessert(
            "Hello Berry",
            "Strawberry",
            "$2",
            "A donut is a round, deep fried cake, usually with a hole in the center. Your favorite breakfast might be a chocolate donut and a cup of coffee.",
            R.drawable.donut_4,
            Green1
        ),
        Dessert(
            "Ice Blueberry",
            "Blueberry",
            "$7",
            "A donut is a round, deep fried cake, usually with a hole in the center. Your favorite breakfast might be a chocolate donut and a cup of coffee.",
            R.drawable.donut_5,
            Orange1
        ),
        Dessert(
            "Cloud Berry",
            "Strawberry",
            "$2",
            "A donut is a round, deep fried cake, usually with a hole in the center. Your favorite breakfast might be a chocolate donut and a cup of coffee.",
            R.drawable.donut_6,
            Purple1
        )
    )

    val menuItems = listOf(
        MenuDessert(1, R.drawable.donut_order), MenuDessert(2, R.drawable.cupcake_order)
    )
}