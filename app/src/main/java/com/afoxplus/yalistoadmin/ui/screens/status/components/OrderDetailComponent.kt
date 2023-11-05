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
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitCardOrderType
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitOrderType
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitOrderTypeVO
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
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
        colors = CardDefaults.cardColors(containerColor = UIKitTheme.colors.light01)
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
                    color = UIKitTheme.colors.blueGray800,
                    style = UIKitTheme.typography.header05SemiBold
                )
                Text(
                    text = orderDate,
                    color = UIKitTheme.colors.blueGray300,
                    style = UIKitTheme.typography.paragraph01
                )
            }
            UIKitCardOrderType(
                modifier = Modifier
                    .width(63.dp),
                orderTypeVO = UIKitOrderTypeVO(orderType.title, orderType.description),
                orderType = if (orderType.code == "DELI") UIKitOrderType.Delivery() else UIKitOrderType.Table()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailComponentPreview() {
    UIKitTheme {
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
