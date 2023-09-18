package com.afoxplus.yalistoadmin.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.afoxplus.uikitcompose.ui.components.ButtonOutlineYaListoComponent
import com.afoxplus.uikitcompose.ui.components.ButtonYaListoComponent
import com.afoxplus.uikitcompose.ui.components.ToolbarComponent
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.Light04
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.ui.screens.details.components.OrderDetailItem
import com.afoxplus.yalistoadmin.ui.screens.details.components.OrderDetailTotalItem
import com.afoxplus.yalistoadmin.ui.screens.details.components.OrderTypeComponent
import com.afoxplus.yalistoadmin.ui.screens.details.components.OrderWhatsappContactComponent

@Composable
fun OrderStatusScreen(
    orderViewModel: OrderViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val order = orderViewModel.orderState.collectAsState().value ?: return
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Light04)
    ) {
        val (toolbar, contentBox, footer) = createRefs()

        ToolbarComponent(
            modifier = Modifier
                .background(color = Light01)
                .constrainAs(toolbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            title = "Client",
            description = order.client.name
        ) {
            navigateBack()
        }

        LazyColumn(
            modifier = Modifier
                .constrainAs(contentBox) {
                    top.linkTo(toolbar.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(footer.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
                OrderWhatsappContactComponent(
                    phoneNumber = order.client.cel,
                    description = order.client.addressReference
                )
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

        ConstraintLayout(
            modifier = Modifier
                .background(Light01)
                .constrainAs(footer) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            val (textState, buttonUpdate, buttonPrint) = createRefs()
            Text(
                modifier = Modifier
                    .height(35.dp)
                    .background(Dark05)
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .constrainAs(textState) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                text = order.state,
                textAlign = TextAlign.Center,
                color = Light01,
                style = Header05SemiBold
            )

            ButtonYaListoComponent(
                modifier = Modifier.constrainAs(buttonUpdate) {
                    top.linkTo(textState.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = stringResource(id = R.string.order_update_state)
            ) {
                orderViewModel.sendOrderState("state")
            }

            ButtonOutlineYaListoComponent(
                modifier = Modifier.constrainAs(buttonPrint) {
                    top.linkTo(buttonUpdate.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = stringResource(id = R.string.order_print)
            ) {
            }
        }
    }
}
