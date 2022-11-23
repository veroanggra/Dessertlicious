package com.veroanggra.dessertlicious.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.dessertlicious.R
import com.veroanggra.dessertlicious.data.DessertData

@Composable
fun DessertItem(
    name: String,
    flavor: String,
    price: String,
    desc: String,
    photo: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 100.dp,
        animationSpec = spring(Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessLow)
    )
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(15.dp), modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(photo),
                contentDescription = null,
                modifier = modifier.size(animatedSizeDp)
            )
            Spacer(modifier = modifier.width(5.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 5.dp, end = 10.dp)
            ) {
                Text(text = name, color = Color.DarkGray, fontSize = 16.sp)
                Text(text = flavor, color = Color.Gray, fontSize = 14.sp)
                Spacer(
                    modifier = modifier
                        .height(5.dp)
                )
                if (isExpanded) {
                    Text(
                        text = desc,
                        color = Color.Gray,
                        fontSize = 10.sp
                    )
                }
            }
            Column(modifier= modifier.padding(end = 8.dp)) {
                Text(
                    text = price,
                    color = Color.Gray,
                    fontSize = 24.sp,
                    modifier = modifier.padding(end = 5.dp)
                )
                Spacer(modifier = modifier.height(5.dp))
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = modifier
                        .background(color = color, shape = CircleShape)
                        .padding(5.dp)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                        contentDescription = if (isExpanded) stringResource(R.string.show_less) else stringResource(
                            R.string.show_more
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DessertItemPreview() {
    DessertItem(
        name = DessertData.desserts[1].name,
        flavor = DessertData.desserts[1].flavor,
        price = DessertData.desserts[1].price,
        desc = DessertData.desserts[1].desc,
        photo = DessertData.desserts[1].photo,
        color = DessertData.desserts[1].color
    )
}