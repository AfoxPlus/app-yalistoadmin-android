package com.afoxplus.yalistoadmin.delivery.components.orders

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.afoxplus.uikit.designsystem.atoms.UIKitButtonOutlineLarge
import com.afoxplus.uikit.designsystem.atoms.UIKitButtonPrimaryLarge
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.cross.utils.generateOrderPDF
import com.afoxplus.yalistoadmin.cross.utils.sharePDF
import com.afoxplus.yalistoadmin.delivery.components.status.OrderDetailItem
import com.afoxplus.yalistoadmin.delivery.components.status.OrderDetailTotalItem
import com.afoxplus.yalistoadmin.delivery.components.status.OrderStatusPrint
import com.afoxplus.yalistoadmin.delivery.components.status.OrderTypeComponent
import com.afoxplus.yalistoadmin.delivery.components.status.OrderWhatsappContactComponent
import com.afoxplus.yalistoadmin.delivery.viewmodels.OrderDetailViewModel.OrderStateButtonView
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun OrderDetailButtons(
    buttonState: OrderStateButtonView,
    onConfirm: () -> Unit,
    onReject: () -> Unit,
    onDone: () -> Unit,
    onArchive: () -> Unit
) {
    when (buttonState) {
        OrderStateButtonView.Confirm -> OrderBottomContent {
            UIKitButtonPrimaryLarge(
                text = stringResource(id = R.string.order_details_button_confirm),
                onClick = onConfirm
            )
            UIKitButtonOutlineLarge(
                text = stringResource(id = R.string.order_details_button_reject),
                onClick = onReject
            )
        }

        OrderStateButtonView.Finish -> OrderBottomContent {
            UIKitButtonPrimaryLarge(
                text = stringResource(id = R.string.order_details_button_finish),
                onClick = onDone
            )
            UIKitButtonOutlineLarge(
                text = stringResource(id = R.string.order_details_button_reject),
                onClick = onReject
            )
        }

        OrderStateButtonView.Reject -> OrderBottomContent {
            UIKitButtonPrimaryLarge(
                text = stringResource(id = R.string.order_archive),
                onClick = onArchive
            )
        }

        OrderStateButtonView.None -> {}
    }
}

@Composable
fun OrderBottomContent(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .background(UIKitTheme.colors.light01)
            .fillMaxWidth()
            .padding(UIKitTheme.spacing.spacing16),
        verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing12)
    ) {
        content()
    }
}

@Composable
fun OrderDetailContent(order: Order) {
    val context = LocalContext.current
    LazyColumn(contentPadding = PaddingValues(UIKitTheme.spacing.spacing12)) {
        item {
            OrderStatusPrint(orderState = order.state) {
                val filePath = context.generateOrderPDF(order)
                context.sharePDF(filePath)
            }
            if (order.client.cel.isNotEmpty() || order.client.addressReference.isNotEmpty()) {
                Spacer(modifier = Modifier.height(UIKitTheme.spacing.spacing08))
                OrderWhatsappContactComponent(
                    phoneNumber = order.client.cel,
                    description = order.client.addressReference
                ) {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                String.format(
                                    "https://api.whatsapp.com/send?phone=%s&text=%s",
                                    order.client.cel,
                                    "Hello this is a new client"
                                )
                            )
                        )
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            OrderTypeComponent(
                orderId = order.number,
                orderDate = order.date,
                orderType = order.orderType
            )
        }
        items(order.detail.size) {
            OrderDetailItem(product = order.detail[it])
            HorizontalDivider(modifier = Modifier.height(1.dp), color = UIKitTheme.colors.gray100)
        }
        item {
            OrderDetailTotalItem(total = order.total, paymentMethod = order.paymentMethod)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
