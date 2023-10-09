package com.afoxplus.yalistoadmin.ui.screens.details.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.yalistoadmin.domain.entities.Order

class OrderShareView @JvmOverloads constructor(
    private val order: Order,
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
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
                Divider(modifier = Modifier.height(1.dp), color = Light03)
            }

            item {
                OrderDetailTotalItem(total = order.total, paymentMethod = order.paymentMethod)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

    fun capture(view: OrderShareView) {
        val bitmap = ImageUtils.generateShareImage(view)
        ShareUtils.shareImageToOthers(context, "test", bitmap)
    }
}
