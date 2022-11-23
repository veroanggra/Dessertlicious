package com.veroanggra.dessertlicious.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.veroanggra.dessertlicious.const.DragTarget
import com.veroanggra.dessertlicious.model.MenuDessert
import com.veroanggra.dessertlicious.ui.theme.Pink1

@Composable
fun TopMenuItem(modifier: Modifier = Modifier, dessert: MenuDessert) {
    DragTarget(modifier = modifier.size(60.dp), dataToDrop = dessert) {
        Image(
            painter = painterResource(id = dessert.imageMenu),
            contentDescription = null,
            modifier
                .clip(
                    RoundedCornerShape(100.dp)
                )
                .size(100.dp)
                .padding(10.dp)
                .background(color = Pink1, shape = RoundedCornerShape(10.dp))
        )
    }
}