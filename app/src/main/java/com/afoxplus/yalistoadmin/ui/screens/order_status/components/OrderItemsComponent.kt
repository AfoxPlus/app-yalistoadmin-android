package com.afoxplus.yalistoadmin.ui.screens.order_status.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.domain.entities.Product

@Composable
fun OrderItemsComponent(
    modifier: Modifier = Modifier,
    list: List<Product>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Light01),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(list.size) {
            OrderDetailItem(product = list[it])
            Divider(modifier = Modifier.height(1.dp), color = Light03)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderItemsComponentPreview() {
    UiKitComposeTheme {
        OrderItemsComponent(
            list = listOf(
                Product(
                    productId = "02",
                    title = "Ají de gallina",
                    quantity = 2,
                    description = "",
                    unitPrice = "",
                    productType = "Menu",
                    subTotal = "",
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
                ),
                Product(
                    productId = "02",
                    title = "Lomo saltado",
                    quantity = 1,
                    description = "",
                    unitPrice = "S/. 20.00",
                    productType = "",
                    subTotal = "S/. 20.00",
                    subDetail = arrayListOf()
                ),
                Product(
                    productId = "02",
                    title = "Arroz tapado",
                    quantity = 1,
                    description = "",
                    unitPrice = "",
                    productType = "Menu",
                    subTotal = "",
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
                        )
                    )
                ),
                Product(
                    productId = "03",
                    title = "Ajiaco de cuy",
                    quantity = 2,
                    description = "",
                    unitPrice = "",
                    productType = "Menu",
                    subTotal = "",
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
        )
    }
}
