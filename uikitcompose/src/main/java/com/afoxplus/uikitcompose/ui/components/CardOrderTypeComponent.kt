package com.afoxplus.uikitcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Dark03
import com.afoxplus.uikitcompose.ui.theme.Dark04
import com.afoxplus.uikitcompose.ui.theme.Header03Bold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Paragraph02
import com.afoxplus.uikitcompose.ui.theme.Red01

@Composable
fun CardOrderTypeComponent(
    modifier: Modifier,
    orderTypeVO: OrderTypeVO,
    orderType: OrderType
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .background(color = orderType.backgroundColor, RoundedCornerShape(4.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp, start = 6.dp, end = 6.dp)
                .background(color = orderType.backgroundColor)
                .align(Alignment.Center)
        ) {
            if (orderType is OrderType.Delivery) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = orderTypeVO.title,
                    color = orderType.textTitleColor,
                    style = Paragraph02
                )
            } else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = orderTypeVO.title,
                    color = orderType.textTitleColor,
                    style = Paragraph02
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = orderTypeVO.description ?: "",
                    color = orderType.textDescriptionColor ?: Light01,
                    style = Header03Bold
                )
            }
        }

    }
}

@Preview
@Composable
fun CardOrderTypeComponentPreview() {
    Column {
        CardOrderTypeComponent(
            modifier = Modifier.fillMaxWidth(),
            orderTypeVO = OrderTypeVO("Mesa", "01"),
            orderType = OrderType.Table()
        )
        CardOrderTypeComponent(
            modifier = Modifier.fillMaxWidth(),
            orderTypeVO = OrderTypeVO("Delivery"),
            orderType = OrderType.Delivery()
        )
        CardOrderTypeComponent(
            modifier = Modifier.fillMaxWidth(),
            orderTypeVO = OrderTypeVO("Total", "S/ 999.80"),
            orderType = OrderType.Amount()
        )
    }
}

data class OrderTypeVO(
    val title: String,
    val description: String? = null
)

sealed class OrderType(
    val backgroundColor: Color,
    val textTitleColor: Color,
    val textDescriptionColor: Color? = null
) {
    class Table : OrderType(Dark03, Light01, Light01)

    class Delivery : OrderType(Red01, Light01)

    class Amount : OrderType(Light01, Dark04, Dark01)
}