package com.afoxplus.yalistoadmin.delivery.screens.home.navbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.delivery.screens.ShareTabOrdersScreen
import com.afoxplus.yalistoadmin.delivery.viewmodels.HomeViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order

@Composable
fun OrdersHomeScreen(
    viewModel: HomeViewModel,
    navigateToOrderDetail: (Order) -> Unit,
    navigateToOrderDetailAdmin: (Order) -> Unit
) {
    LaunchedEffect(key1 = Unit) { viewModel.getStatesTabOrder() }
    val orderPendingId by viewModel.ordersPendingState.collectAsStateWithLifecycle()

    ShareTabOrdersScreen(
        viewModel = viewModel,
        stateId = orderPendingId,
        navigateToOrderDetail = navigateToOrderDetail,
        navigateToOrderDetailAdmin = navigateToOrderDetailAdmin
    )
}
