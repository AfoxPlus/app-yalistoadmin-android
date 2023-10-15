package com.afoxplus.yalistoadmin.ui.screens.status.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.R

@Composable
fun OrderTypeComponent(
    modifier: Modifier = Modifier,
    orderId: String,
    orderDate: String,
    orderType: com.afoxplus.yalistoadmin.domain.entities.OrderType
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Light01)
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
                    .width(63.dp),
                orderTypeVO = OrderTypeVO(orderType.title, orderType.description),
                orderType = if (orderType.code == "DELI") OrderType.Delivery() else OrderType.Table()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailComponentPreview() {
    UiKitComposeTheme {
        OrderTypeComponent(
            orderId = "#000001",
            orderDate = "04 Jul 2023, 11:26 PM",
            orderType = com.afoxplus.yalistoadmin.domain.entities.OrderType(
                code = "SALON",
                title = "SALON",
                description = "06"
            )
        )
    }
}
