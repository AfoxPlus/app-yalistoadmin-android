package com.afoxplus.yalistoadmin.delivery.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.afoxplus.uikit.designsystem.atoms.UIKitButtonOutlineLarge
import com.afoxplus.uikit.designsystem.atoms.UIKitButtonPrimaryLarge
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.molecules.UIKitTopBar
import com.afoxplus.uikit.designsystem.organisms.UIKitBottomSheet
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.cross.utils.generateOrderPDF
import com.afoxplus.yalistoadmin.cross.utils.sharePDF
import com.afoxplus.yalistoadmin.delivery.components.status.OrderDetailItem
import com.afoxplus.yalistoadmin.delivery.components.status.OrderDetailTotalItem
import com.afoxplus.yalistoadmin.delivery.components.status.OrderStatusPrint
import com.afoxplus.yalistoadmin.delivery.components.status.OrderTypeComponent
import com.afoxplus.yalistoadmin.delivery.components.status.OrderWhatsappContactComponent
import com.afoxplus.yalistoadmin.delivery.viewmodels.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderStatusScreen(
    orderViewModel: OrderViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val order = orderViewModel.orderState.collectAsState().value ?: return
    val context = LocalContext.current

    val statesOrder = orderViewModel.states.collectAsState().value
    val stateSelected = orderViewModel.stateSelected.collectAsState().value
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        orderViewModel.orderArchived.collect {
            navigateBack()
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(UIKitTheme.colors.orange25)
    ) {
        val (toolbar, contentBox, footer) = createRefs()

        UIKitTopBar(
            modifier = Modifier
                .background(color = UIKitTheme.colors.light01)
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
                OrderStatusPrint(orderState = order.state) {
                    val filePath = context.generateOrderPDF(order)
                    context.sharePDF(filePath)
                    orderViewModel.updateOrderStateFromPrint()
                }
                if (order.client.cel.isNotEmpty() || order.client.addressReference.isNotEmpty()) {
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

        ConstraintLayout(
            modifier = Modifier
                .background(UIKitTheme.colors.light01)
                .constrainAs(footer) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            val (buttonUpdate, buttonPrint) = createRefs()

            UIKitButtonPrimaryLarge(
                modifier = Modifier.constrainAs(buttonUpdate) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = if (orderViewModel.isUpdateButton()) {
                    stringResource(id = R.string.order_update_state)
                } else {
                    stringResource(
                        id = R.string.order_archive
                    )
                },
                onClick = {
                    if (orderViewModel.isUpdateButton()) {
                        isSheetOpen = true
                    } else {
                        orderViewModel.archiveOrder()
                    }
                }
            )

            UIKitButtonOutlineLarge(
                modifier = Modifier.constrainAs(buttonPrint) {
                    top.linkTo(buttonUpdate.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = stringResource(id = R.string.order_print)
            ) {
                val filePath = context.generateOrderPDF(order)
                context.sharePDF(filePath)
                orderViewModel.updateOrderStateFromPrint()
            }

            if (isSheetOpen) {
                UIKitBottomSheet(
                    title = stringResource(id = R.string.order_states_list),
                    list = statesOrder,
                    description = {
                        it.name
                    },
                    showIcon = {
                        it.isCheck
                    },
                    onClick = {
                        isSheetOpen = false
                        orderViewModel.updateCheckState(it.name)
                        orderViewModel.sendOrderState(it.id)
                    },
                    onDismiss = {
                        isSheetOpen = false
                    },
                    sheetState = sheetState
                )
            }
        }
    }
}
