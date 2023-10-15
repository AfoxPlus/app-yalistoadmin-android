package com.afoxplus.yalistoadmin.ui.screens.order_status.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.afoxplus.uikitcompose.ui.theme.Dark02
import com.afoxplus.uikitcompose.ui.theme.Dark04
import com.afoxplus.uikitcompose.ui.theme.Gray01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01SemiBold
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Product

@Composable
fun OrderAppetizerItemComponent(modifier: Modifier = Modifier, product: Product) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (title, quantity) = createRefs()
        Text(
            modifier = modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(quantity.start)
                    width = Dimension.fillToConstraints
                },
            text = product.title,
            textAlign = TextAlign.Start,
            color = Dark04,
            style = Paragraph01
        )

        Text(
            modifier = modifier.constrainAs(quantity) {
                top.linkTo(title.top)
                bottom.linkTo(title.bottom)
                end.linkTo(parent.end)
            },
            text = stringResource(id = R.string.order_quantity, product.quantity),
            color = Dark02,
            style = Paragraph01
        )
    }
}

@Composable
fun OrderAppetizersComponent(modifier: Modifier = Modifier, list: List<Product>) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(
                list.size
                    .plus(1)
                    .times(23).dp
            ),
        userScrollEnabled = false
    ) {
        item {
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
        }
        item {
            Text(
                modifier = modifier
                    .padding(0.dp, 4.dp, 0.dp, 0.dp),
                text = stringResource(id = R.string.order_appetizers),
                color = Dark02,
                style = Paragraph01SemiBold
            )
        }
        items(list.size) {
            OrderAppetizerItemComponent(product = list[it])
        }
    }
}

@Composable
@Preview(showBackground = true)
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
