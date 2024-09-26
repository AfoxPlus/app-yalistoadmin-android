package com.afoxplus.yalistoadmin.delivery.screens.home.navbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.delivery.components.orders.OrdersComponent
import com.afoxplus.yalistoadmin.delivery.viewmodels.TabOrderViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.OrderStateCode

@Composable
fun OrdersHomeScreen(
    tabOrderViewModel: TabOrderViewModel = hiltViewModel(),
    navigateToOrderDetail: (Order) -> Unit,
    navigateToOrderDetailAdmin: (Order) -> Unit
) {
    val ordersState by tabOrderViewModel.ordersState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        tabOrderViewModel.getOrdersByState(OrderStateCode.TODO)
    }
    when (ordersState) {
        TabOrderViewModel.OrderState.Failure -> {}
        TabOrderViewModel.OrderState.Loading -> HandleShowLoading()
        is TabOrderViewModel.OrderState.Success -> OrdersComponent(orders = (ordersState as TabOrderViewModel.OrderState.Success).data, onClick = { navigateToOrderDetail(it) }, onLongClick = { navigateToOrderDetailAdmin(it) })
    }
}