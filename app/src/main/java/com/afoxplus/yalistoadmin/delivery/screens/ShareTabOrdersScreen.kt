package com.afoxplus.yalistoadmin.delivery.screens

import androidx.compose.runtime.Composable
import com.afoxplus.yalistoadmin.delivery.components.orders.OrdersComponent
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.HandleShowLoading
import com.afoxplus.yalistoadmin.delivery.viewmodels.TabOrderViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun ShareTabOrdersScreen(
    ordersState: TabOrderViewModel.OrderState,
    navigateToOrderDetail: (Order) -> Unit,
    navigateToOrderDetailAdmin: (Order) -> Unit
) {
    when (ordersState) {
        TabOrderViewModel.OrderState.Failure -> {}
        TabOrderViewModel.OrderState.Loading -> HandleShowLoading()
        is TabOrderViewModel.OrderState.Success -> OrdersComponent(orders = ordersState.data, onClick = { navigateToOrderDetail(it) }, onLongClick = { navigateToOrderDetailAdmin(it) })
    }
}
