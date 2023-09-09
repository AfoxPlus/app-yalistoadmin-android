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

@Composable
fun OrderTypeComponent(
    modifier: Modifier = Modifier,
    orderId: String,
    orderDate: String
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
                text = orderId,
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
            orderTypeVO = OrderTypeVO("Delivery"),
            orderType = OrderType.Delivery()
        )
    }
}

@Composable
fun OrderDetailComponent(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Light01)
    ) {
        OrderTypeComponent(
            modifier = modifier,
            orderId = "ID: #000451",
            orderDate = "05 Feb 2023, 08:28 PM"
        )
        OrderItemsComponent()
        Divider(modifier = Modifier.height(1.dp), color = Light03)
        OrderDetailTotalItem(total = "S/ 50.30", paymentMethod = "Efectivo")
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailComponentPreview() {
    UiKitComposeTheme {
        OrderDetailComponent()
    }
}
