package com.afoxplus.yalistoadmin.delivery.components.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.yalistoadmin.domain.entities.Product

@Composable
fun OrderItemsComponent(
    modifier: Modifier = Modifier,
    list: List<Product>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(color = UIKitTheme.colors.light01),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(list.size) {
            OrderDetailItem(product = list[it])
            HorizontalDivider(modifier = Modifier.height(1.dp), color = UIKitTheme.colors.gray100)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderItemsComponentPreview() {
    UIKitTheme {
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
                    notes = "",
                    subDetail = listOf(
                        Product(
                            productId = "02",
                            title = "Papa a la huancaina",
                            quantity = 1,
                            description = "",
                            unitPrice = "",
                            productType = "",
                            subTotal = "",
                            notes = "",
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
                            notes = "",
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
                    notes = "",
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
                    notes = "",
                    subDetail = listOf(
                        Product(
                            productId = "02",
                            title = "Papa a la huancaina",
                            quantity = 1,
                            description = "",
                            unitPrice = "",
                            productType = "",
                            subTotal = "",
                            notes = "",
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
                    notes = "",
                    subDetail = listOf(
                        Product(
                            productId = "02",
                            title = "Papa a la huancaina",
                            quantity = 1,
                            description = "",
                            unitPrice = "",
                            productType = "",
                            subTotal = "",
                            notes = "",
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
                            notes = "",
                            subDetail = arrayListOf()
                        )
                    )
                )
            )
        )
    }
}
