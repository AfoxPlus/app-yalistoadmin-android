package com.afoxplus.yalistoadmin.delivery.screens.home.navbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.delivery.routers.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.delivery.screens.OrderScreen
import com.afoxplus.yalistoadmin.delivery.viewmodels.HomeViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun OrdersHomeScreen(
    route: BottomBarHomeRouter = BottomBarHomeRouter.Orders,
    viewModel: HomeViewModel,
    navigateTo: (Order) -> Unit
) {
    LaunchedEffect(key1 = Unit) { viewModel.getStatesTabOrder() }
    val orderPendingId by viewModel.ordersPendingState.collectAsStateWithLifecycle()

    OrderScreen(
        viewModel = viewModel,
        route = route,
        stateId = orderPendingId,
        navigateTo = navigateTo
    )
}
