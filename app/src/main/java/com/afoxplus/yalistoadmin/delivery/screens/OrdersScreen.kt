package com.afoxplus.yalistoadmin.delivery.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.delivery.components.orders.OrdersComponent
import com.afoxplus.yalistoadmin.delivery.routers.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.HandleShowLoading
import com.afoxplus.yalistoadmin.delivery.viewmodels.HomeViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun OrderScreen(
    viewModel: HomeViewModel,
    route: BottomBarHomeRouter,
    stateId: String? = "",
    navigateTo: (Order) -> Unit
) {
    val auth by viewModel.auth.collectAsStateWithLifecycle()
    val ordersState by viewModel.ordersState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) { viewModel.getAuth() }
    LaunchedEffect(key1 = auth) {
        if (auth.code != "" && stateId != null) {
            viewModel.getStatus(restaurantCode = auth.code, stateId = stateId)
        }
    }
    when (ordersState) {
        HomeViewModel.OrderState.Failure -> {}
        HomeViewModel.OrderState.Loading -> HandleShowLoading()
        is HomeViewModel.OrderState.Success -> OrdersComponent(orders = (ordersState as HomeViewModel.OrderState.Success).data, onClick = { navigateTo(it) })
    }
}
