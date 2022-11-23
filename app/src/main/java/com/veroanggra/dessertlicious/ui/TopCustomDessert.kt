package com.veroanggra.dessertlicious.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.veroanggra.dessertlicious.R
import com.veroanggra.dessertlicious.const.AppBarCollapsedHeight
import com.veroanggra.dessertlicious.const.AppBarExpendedHeight
import com.veroanggra.dessertlicious.const.DropTarget
import com.veroanggra.dessertlicious.const.LongPressDraggable
import com.veroanggra.dessertlicious.data.DessertData
import com.veroanggra.dessertlicious.data.DessertData.menuItems
import com.veroanggra.dessertlicious.model.MenuDessert
import kotlin.math.min

@Composable
fun TopCustomDessert(modifier: Modifier = Modifier, scrollState: LazyListState) {
    val columnHeight = AppBarExpendedHeight - AppBarCollapsedHeight
    val maxOffset =
        with(LocalDensity.current) { columnHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top
    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            LongPressDraggable(modifier.fillMaxSize()) {
                LazyRow() {
                    items(items = menuItems) { menu ->
                        TopMenuItem(dessert = menu)
                    }
                }
                KitchenScreen()
            }
        }
    }
}

@Composable
fun BoxScope.KitchenScreen(modifier: Modifier = Modifier) {
    var isOrder by remember {
        mutableStateOf(false)
    }
    val menuDesserts = remember {
        mutableStateMapOf<Int, MenuDessert>()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 70.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DropTarget<MenuDessert>(
            modifier = Modifier
                .padding(6.dp)
        ) { isInBound, menuDessert ->
            if (isInBound) isOrder = true

            val animatedSizeDp by animateDpAsState(
                targetValue = if (isInBound) 270.dp else 250.dp,
                animationSpec = spring(Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessLow)
            )

            menuDessert?.let {
                if (isInBound)
                    menuDesserts[menuDessert.key] = menuDessert
            }

            Column(
                modifier = modifier
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.plate),
                        contentDescription = null, modifier.size(animatedSizeDp)
                    )
                    if (isOrder) {
                        if (menuDesserts.isNotEmpty()) {
                            Image(
                                painter = painterResource(id = menuDesserts.values.sumOf { it.imageMenu }),
                                contentDescription = null,
                                modifier
                                    .size(230.dp)
                                    .padding(top = 25.dp, start = 32.dp)
                            )
                        }
                    } else {
                        menuDesserts.values.clear()
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Change Menu", modifier.clickable { isOrder = false })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopCustomPreview() {
//    TopCustomDessert()
}