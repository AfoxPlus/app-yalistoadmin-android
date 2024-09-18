package com.afoxplus.yalistoadmin.delivery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.molecules.UIKitTopBar
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.delivery.components.orders.OrderDetailButtons
import com.afoxplus.yalistoadmin.delivery.components.orders.OrderDetailContent
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.HandleShowLoading
import com.afoxplus.yalistoadmin.delivery.viewmodels.OrderDetailViewModel
import com.afoxplus.yalistoadmin.delivery.viewmodels.OrderDetailViewModel.OrderStateButtonView
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun OrderDetailScreen(orderDetailViewModel: OrderDetailViewModel = hiltViewModel(), navigateBack: () -> Unit) {
    val orderState by orderDetailViewModel.orderState.collectAsState()
    val orderButtonSate by orderDetailViewModel.orderButtonState.collectAsState()
    when (orderState) {
        OrderDetailViewModel.OrderStateView.Loading -> HandleShowLoading()
        is OrderDetailViewModel.OrderStateView.Success -> {
            val order = (orderState as OrderDetailViewModel.OrderStateView.Success).data
            HandleOrderDetailScreen(
                orderButtonSate,
                order,
                onConfirm = {
                    orderDetailViewModel.updateOrderStateToProgress()
                },
                onReject = {
                    orderDetailViewModel.updateOrderStateToReject()
                },
                onDone = {
                    orderDetailViewModel.updateOrderStateToDone()
                },
                onArchive = {
                    orderDetailViewModel.archiveOrder()
                },
                navigateBack = navigateBack
            )
        }
    }
    LaunchedEffect(Unit) { orderDetailViewModel.setupOrderDetail() }
    LaunchedEffect(key1 = Unit) {
        orderDetailViewModel.orderArchived.collect {
            navigateBack()
        }
    }
}

@Composable
fun HandleOrderDetailScreen(
    orderButtonSate: OrderStateButtonView,
    order: Order,
    onConfirm: () -> Unit,
    onReject: () -> Unit,
    onDone: () -> Unit,
    onArchive: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                modifier = Modifier.background(color = UIKitTheme.colors.light01),
                title = stringResource(id = R.string.order_status_client),
                description = order.client.name
            ) {
                navigateBack()
            }
        },
        bottomBar = {
            OrderDetailButtons(
                orderButtonSate,
                onConfirm = onConfirm,
                onReject = onReject,
                onDone = onDone,
                onArchive = onArchive
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(UIKitTheme.colors.orange25)
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            OrderDetailContent(order)
        }
    }
}
