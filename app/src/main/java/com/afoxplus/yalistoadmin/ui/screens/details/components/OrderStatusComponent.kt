package com.afoxplus.yalistoadmin.ui.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Dark02
import com.afoxplus.uikitcompose.ui.theme.Dark03
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Header02Bold
import com.afoxplus.uikitcompose.ui.theme.Header05
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01Bold
import com.afoxplus.uikitcompose.ui.theme.Paragraph02
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Product

@Composable
fun OrderDetailItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        if (product.isMenu()) {
            Column(
                modifier = Modifier
                    .background(color = Dark03, shape = RoundedCornerShape(4.dp))
                    .padding(4.dp)
            ) {
                Text(
                    text = product.productType ?: "Menu",
                    color = Light01,
                    style = Paragraph02,
                    textAlign = TextAlign.Center
                )
            }
        }
        Text(
            text = product.title,
            color = Dark02,
            style = Header05
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = product.unitPrice ?: "",
                color = Dark02,
                style = Paragraph01
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(
                    R.string.order_quantity,
                    product.quantity.toString()
                ),
                color = Dark02,
                style = Paragraph01
            )
            Text(
                modifier = Modifier.weight(1f),
                text = product.subTotal ?: "",
                color = Dark02,
                textAlign = TextAlign.End,
                style = Paragraph01Bold
            )
        }
        if (product.isMenu()) {
            OrderAppetizersComponent(
                modifier = modifier.padding(0.dp, 4.dp, 0.dp, 0.dp),
                list = product.subDetail
            )
        }
    }
}

@Composable
fun OrderDetailTotalItem(modifier: Modifier = Modifier, total: String, paymentMethod: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = stringResource(id = R.string.order_status_total_label),
            color = Dark02,
            style = Paragraph01
        )
        Text(
            text = total,
            color = Dark02,
            style = Header02Bold
        )
        Text(
            text = stringResource(id = R.string.order_status_payment_method_label),
            color = Dark02,
            style = Paragraph01
        )
        Text(
            text = paymentMethod,
            color = Dark05,
            style = Paragraph02
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailItemPreview() {
    UiKitComposeTheme {
        Column {
            OrderDetailItem(
                product = Product(
                    productId = "02",
                    title = "Ají de gallina",
                    quantity = 2,
                    description = "",
                    unitPrice = "S/ 20.00",
                    productType = "Menu",
                    subTotal = "S/ 40.00",
                    subDetail = listOf(
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
            )
            Divider(modifier = Modifier.height(1.dp), color = Light03)
            OrderDetailItem(
                product = Product(
                    productId = "02",
                    title = "Lomo saltado",
                    quantity = 2,
                    description = "",
                    unitPrice = "S/. 30.00",
                    productType = "",
                    subTotal = "S/. 60.00",
                    subDetail = listOf()
                )
            )
            Divider(modifier = Modifier.height(1.dp), color = Light03)
            OrderDetailTotalItem(total = "S/ 50.30", paymentMethod = "Efectivo")
        }
    }
}
