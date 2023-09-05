package com.afoxplus.yalistoadmin.ui.orders.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.components.CardOrderTypeComponent
import com.afoxplus.uikitcompose.ui.components.OrderType
import com.afoxplus.uikitcompose.ui.components.OrderTypeVO
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Dark04
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Gray01
import com.afoxplus.uikitcompose.ui.theme.Header04Bold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.Paragraph01SemiBold
import com.afoxplus.uikitcompose.ui.theme.Paragraph02
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Client
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.Product

@Composable
fun ItemOrderComponent(
    modifier: Modifier = Modifier,
    order: Order
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Light03, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Text(
                    text = stringResource(id = R.string.order_status_client),
                    color = Dark04,
                    style = Paragraph02
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = order.client.name, color = Dark01, style = Paragraph01SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    CardOrderTypeComponent(
                        modifier = Modifier.weight(1.5f),
                        orderTypeVO = OrderTypeVO(
                            order.orderType.title,
                            description = order.orderType.description
                        ),
                        orderType = if (order.orderType.code == "DELI") OrderType.Delivery() else OrderType.Table()
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CardOrderTypeComponent(
                        modifier = Modifier.weight(3f),
                        orderTypeVO = OrderTypeVO(
                            stringResource(id = R.string.order_status_total),
                            description = order.total
                        ),
                        orderType = OrderType.Amount()
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp, end = 10.dp)
                    .width(1.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Canvas(modifier = Modifier.fillMaxHeight()) {
                    val pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(size.height / 35, size.height / 35),
                        0f
                    )
                    drawLine(
                        color = Gray01,
                        strokeWidth = 2.dp.toPx(),
                        start = Offset(x = size.width / 2, y = 0f),
                        end = Offset(x = size.width / 2, y = size.height),
                        pathEffect = pathEffect
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.3f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Dark05, shape = RoundedCornerShape(10.dp))
                        .padding(4.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = order.state,
                        color = Light01,
                        style = Paragraph02,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = String.format(
                            stringResource(id = R.string.order_status_id),
                            order.number
                        ),
                        color = Dark04,
                        style = Header04Bold,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemOrderComponentPreview() {
    Column {
        ItemOrderComponent(
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
                total = "S/ 66.80",
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
                        subDetail = arrayListOf()
                    )
                )
            )
        )
    }
}
