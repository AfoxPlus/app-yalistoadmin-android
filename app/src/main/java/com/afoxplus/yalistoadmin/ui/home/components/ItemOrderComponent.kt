package com.afoxplus.yalistoadmin.ui.home.components

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

@Composable
fun ItemOrderComponent(
    modifier: Modifier = Modifier
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
                Text(text = "Cliente:", color = Dark04, style = Paragraph02)
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "Juan Carlos del Rio", color = Dark01, style = Paragraph01SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    CardOrderTypeComponent(
                        modifier = Modifier.weight(1.5f),
                        orderTypeVO = OrderTypeVO("Mesa", description = "01"),
                        orderType = OrderType.Table()
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CardOrderTypeComponent(
                        modifier = Modifier.weight(3f),
                        orderTypeVO = OrderTypeVO("Total", description = "S/ 999.80"),
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
                        text = "Pendiente",
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
                        text = "ID: #000451",
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
        ItemOrderComponent()
    }
}
