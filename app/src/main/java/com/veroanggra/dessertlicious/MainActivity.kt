package com.veroanggra.dessertlicious

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.veroanggra.dessertlicious.const.AppBarExpendedHeight
import com.veroanggra.dessertlicious.data.DessertData
import com.veroanggra.dessertlicious.ui.DessertItem
import com.veroanggra.dessertlicious.ui.TopCustomDessert
import com.veroanggra.dessertlicious.ui.theme.DessertliciousTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DessertliciousTheme {
                // A surface container using the 'background' color from the theme
                ProvideWindowInsets {
                    Surface(color = White) {
                        DessertApp()
                    }
                }
            }
        }
    }
}

@Composable
fun DessertList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        items(DessertData.desserts) { dessert ->
            DessertItem(
                name = dessert.name,
                flavor = dessert.flavor,
                price = dessert.price,
                desc = dessert.desc,
                photo = dessert.photo,
                color = dessert.color
            )
        }

    }
}

@Composable
fun DessertApp(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    Box {
        DessertContent(scrollState = scrollState)
        TopCustomDessert(scrollState= scrollState)
    }
}


@Composable
fun DessertContent(modifier: Modifier = Modifier, scrollState: LazyListState) {
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
        item {
            Spacer(modifier = modifier.height(16.dp))
            DessertList()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DessertliciousTheme {
        DessertApp()
    }
}