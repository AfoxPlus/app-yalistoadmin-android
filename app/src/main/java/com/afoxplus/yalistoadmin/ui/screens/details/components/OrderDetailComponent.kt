package com.afoxplus.yalistoadmin.ui.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.components.CardOrderTypeComponent
import com.afoxplus.uikitcompose.ui.components.OrderType
import com.afoxplus.uikitcompose.ui.components.OrderTypeVO
import com.afoxplus.uikitcompose.ui.theme.Dark02
import com.afoxplus.uikitcompose.ui.theme.Gray01
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Client
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.Product

@Composable
fun OrderTypeComponent(
    modifier: Modifier = Modifier,
    orderId: String,
    orderDate: String,
    orderType: com.afoxplus.yalistoadmin.domain.entities.OrderType
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.order_status_id, orderId),
                color = Dark02,
                style = Header05SemiBold
            )
            Text(
                text = orderDate,
                color = Gray01,
                style = Paragraph01
            )
        }
        CardOrderTypeComponent(
            modifier = Modifier
                .width(63.dp)
                .height(48.dp),
            orderTypeVO = OrderTypeVO(orderType.title, orderType.description),
            orderType = OrderType.Delivery()
        )
    }
}

@Composable
fun OrderDetailComponent(
    modifier: Modifier = Modifier,
    order: Order
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Light01)
    ) {
        OrderTypeComponent(
            modifier = modifier,
            orderId = order.number,
            orderDate = order.date,
            orderType = order.orderType
        )
        OrderItemsComponent(list = order.detail)
        Divider(modifier = Modifier.height(1.dp), color = Light03)
        OrderDetailTotalItem(total = order.total, paymentMethod = "Efectivo")
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailComponentPreview() {
    UiKitComposeTheme {
        OrderDetailComponent(
            order = Order(
                id = "64a4f0e9f03e52399e481854",
                number = "#000001",
                date = "04 Jul 2023, 11:26 PM",
                state = "Proceso",
                stateCode = "PROGRESS",
                restaurant = "Kitchen",
                orderType = com.afoxplus.yalistoadmin.domain.entities.OrderType(
                    code = "SALON",
                    title = "SALON",
                    description = "06"
                ),
                total = "S/ 86.40",
                client = Client(
                    name = "Prueba",
                    cel = "966998544",
                    addressReference = "Simbal, calle j4 puerta 250"
                ),
                detail = listOf(
                    Product(
                        productId = "61a5a68c0c327b1d087ccdb3",
                        title = "Pescado",
                        description = "Pescado frito",
                        productType = "",
                        unitPrice = "S/ 18.20",
                        quantity = 2,
                        subTotal = "S/ 36.40",
                        subDetail = listOf()
                    ),
                    Product(
                        productId = "02",
                        title = "Ají de gallina",
                        quantity = 2,
                        description = "",
                        unitPrice = "S/. 25.00",
                        productType = "Menu",
                        subTotal = "S/. 50.00",
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
        )
    }
}
