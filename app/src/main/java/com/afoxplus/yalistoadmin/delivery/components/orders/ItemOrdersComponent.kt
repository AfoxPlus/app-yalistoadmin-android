package com.afoxplus.yalistoadmin.delivery.components.orders

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitCardOrderType
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitOrderType
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitOrderTypeVO
import com.afoxplus.uikit.designsystem.foundations.UIKitColorTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Order

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemOrderComponent(
    modifier: Modifier = Modifier,
    order: Order,
    onClick: (Order) -> Unit,
    onLongClick: (Order) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(onClick = { onClick(order) }, onLongClick = { onLongClick(order) }),
        border = BorderStroke(UIKitTheme.spacing.spacing02, UIKitColorTheme.gray200),
        colors = CardDefaults.cardColors(
            containerColor = UIKitColorTheme.light01
        ),
        shape = RoundedCornerShape(UIKitTheme.spacing.spacing12)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                UIKitText(
                    text = stringResource(id = R.string.order_status_client),
                    color = UIKitTheme.colors.gray700,
                    style = UIKitTheme.typography.paragraph02
                )
                Spacer(modifier = Modifier.height(UIKitTheme.spacing.spacing02))
                UIKitText(
                    text = order.client.name,
                    style = UIKitTheme.typography.paragraph01SemiBold
                )
                Spacer(modifier = Modifier.height(UIKitTheme.spacing.spacing16))
                Row(modifier = Modifier.fillMaxWidth()) {
                    UIKitCardOrderType(
                        modifier = Modifier.weight(1.5f),
                        orderTypeVO = UIKitOrderTypeVO(
                            order.orderType.title,
                            description = order.orderType.description
                        ),
                        orderType = if (order.orderType.code == "DELI") UIKitOrderType.Delivery() else UIKitOrderType.Table()
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    UIKitCardOrderType(
                        modifier = Modifier.weight(3f),
                        orderTypeVO = UIKitOrderTypeVO(
                            stringResource(id = R.string.order_status_total),
                            description = order.total
                        ),
                        orderType = UIKitOrderType.Amount()
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
                        color = UIKitColorTheme.gray400,
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
                        .background(
                            color = UIKitTheme.colors.gray800,
                            shape = RoundedCornerShape(UIKitTheme.spacing.spacing04)
                        )
                        .padding(UIKitTheme.spacing.spacing04)
                ) {
                    UIKitText(
                        modifier = Modifier.fillMaxWidth(),
                        text = order.state,
                        color = UIKitTheme.colors.light01,
                        style = UIKitTheme.typography.paragraph01Bold,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UIKitText(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = String.format(
                            stringResource(id = R.string.order_status_id),
                            order.number
                        ),
                        color = UIKitTheme.colors.gray600,
                        style = UIKitTheme.typography.header04Bold,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}
