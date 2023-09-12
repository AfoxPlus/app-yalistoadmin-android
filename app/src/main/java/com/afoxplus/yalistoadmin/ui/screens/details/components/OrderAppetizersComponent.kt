package com.afoxplus.yalistoadmin.ui.screens.details.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Dark02
import com.afoxplus.uikitcompose.ui.theme.Dark04
import com.afoxplus.uikitcompose.ui.theme.Gray01
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01SemiBold
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Product

@Composable
fun OrderAppetizerItemComponent(modifier: Modifier = Modifier, product: Product) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = product.title,
                color = Dark04,
                style = Paragraph01
            )
        }

        Text(
            text = stringResource(id = R.string.order_quantity, product.quantity),
            color = Dark02,
            style = Paragraph01
        )
    }
}

@Composable
fun OrderAppetizersComponent(modifier: Modifier = Modifier, list: List<Product>) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Light01),
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Canvas(modifier = Modifier.fillMaxWidth()) {
            val pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(size.width / 90, size.width / 70),
                0f
            )
            drawLine(
                color = Gray01,
                strokeWidth = 2.dp.toPx(),
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                pathEffect = pathEffect
            )
        }
        Text(
            modifier = modifier
                .padding(0.dp, 4.dp, 0.dp, 0.dp),
            text = stringResource(id = R.string.order_appetizers),
            color = Dark02,
            style = Paragraph01SemiBold
        )
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Light01),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(list.size) {
                OrderAppetizerItemComponent(product = list[it])
            }
        }
    }
}

@Composable
@Preview
fun OrderAppetizersComponentPreview() {
    UiKitComposeTheme {
        OrderAppetizersComponent(
            list = listOf(
                Product(
                    productId = "02",
                    title = "Papa a la huancaina",
                    quantity = 1,
                    description = "",
                    unitPrice = "",
                    productType = "",
                    subTotal = "",
                    subDetail = arrayListOf()
                ),
                Product(
                    productId = "03",
                    title = "Ensalada cocida",
                    quantity = 1,
                    description = "",
                    unitPrice = "",
                    productType = "",
                    subTotal = "",
                    subDetail = arrayListOf()
                )
            )
        )
    }
}
