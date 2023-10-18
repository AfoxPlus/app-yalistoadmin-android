package com.afoxplus.yalistoadmin.ui.screens.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.ui.screens.orders.components.OrdersComponent

@Composable
fun OrderScreen(
    viewModel: OrdersStatusViewModel,
    stateId: String? = "",
    navigateTo: (Order) -> Unit
) {
    val auth by viewModel.auth.collectAsStateWithLifecycle()
    val orders by viewModel.order.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) { viewModel.getAuth() }
    LaunchedEffect(key1 = auth) {
        if (auth.code != "" && stateId != null) {
            viewModel.getStatus(restaurantCode = auth.code, stateId = stateId)
        }
    }

    Column {
        OrdersComponent(orders = orders, onClick = { navigateTo(it) })
    }
}
