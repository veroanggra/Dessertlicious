package com.veroanggra.dessertlicious.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets
import com.veroanggra.dessertlicious.R
import com.veroanggra.dessertlicious.const.AppBarCollapsedHeight
import com.veroanggra.dessertlicious.const.AppBarExpendedHeight
import com.veroanggra.dessertlicious.const.DropTarget
import com.veroanggra.dessertlicious.const.LongPressDraggable
import com.veroanggra.dessertlicious.data.DessertData
import com.veroanggra.dessertlicious.data.DessertData.menuItems
import com.veroanggra.dessertlicious.data.DessertData.toppingItem
import com.veroanggra.dessertlicious.model.MenuDessert
import com.veroanggra.dessertlicious.model.MenuTopping
import com.veroanggra.dessertlicious.ui.theme.Pink1
import com.veroanggra.dessertlicious.ui.theme.Pink2
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
        Row(modifier = modifier.fillMaxWidth()) {
            LongPressDraggable(
                modifier
                    .fillMaxHeight()
            ) {
                LazyRow(horizontalArrangement = Arrangement.SpaceBetween) {
                    items(items = menuItems) { menu ->
                        TopMenuItem(dessert = menu)
                    }
                    item {
                        Cart()
                    }
                }
                KitchenScreen()
            }
        }
    }
}

@Composable
fun LazyItemScope.Cart(modifier: Modifier = Modifier) {
    Row {
        Spacer(modifier = modifier.width(240.dp))
        Box(contentAlignment = Alignment.CenterEnd) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = modifier
                        .size(35.dp)
                        .padding(top = 5.dp)
                )

            }
        }
    }
}

@Composable
fun BoxScope.KitchenScreen(modifier: Modifier = Modifier) {
    var isOrder by remember {
        mutableStateOf(false)
    }
    var isTopping by remember {
        mutableStateOf(false)
    }
    val menuDesserts = remember {
        mutableStateMapOf<Int, MenuDessert>()
    }
    val menuToppings = remember {
        mutableStateMapOf<Int, MenuTopping>()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DropTarget<MenuDessert>(
                modifier = Modifier
                    .padding(6.dp)
            ) { isInBound, menuDessert ->
                if (isInBound) isOrder = true

                val animatedSizeDp by animateDpAsState(
                    targetValue = if (isInBound) 270.dp else 250.dp,
                    animationSpec = spring(
                        Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )

                menuDessert?.let {
                    if (isInBound)
                        menuDesserts[menuDessert.key] = menuDessert
                }
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
            Text(
                text = "Change Menu",
                modifier.clickable { isOrder = false },
                color = Color.Red,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (isOrder) {
                Text(text = "Variant", color = Color.DarkGray)
                LazyRow {
                    items(items = toppingItem) { topping ->
                        ToppingMenuItem(topping = topping)

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Composition", color = Color.DarkGray)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(id = R.string.ingredients_2),
                    color = Color.DarkGray, fontSize = 12.sp, modifier = modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopCustomPreview() {
//    TopCustomDessert()
}