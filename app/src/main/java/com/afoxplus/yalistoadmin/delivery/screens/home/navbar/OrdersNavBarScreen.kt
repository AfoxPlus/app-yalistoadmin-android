package com.afoxplus.yalistoadmin.delivery.screens.home.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.uikit.designsystem.foundations.UIKitColorTheme
import com.afoxplus.yalistoadmin.delivery.components.orders.OrdersComponent
import com.afoxplus.yalistoadmin.delivery.viewmodels.TabOrderViewModel
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.OrderStateCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersHomeScreen(
    tabOrderViewModel: TabOrderViewModel = hiltViewModel(),
    navigateToOrderDetail: (Order) -> Unit,
    navigateToOrderDetailAdmin: (Order) -> Unit
) {
    val ordersState by tabOrderViewModel.ordersState.collectAsStateWithLifecycle()
    val state = rememberPullToRefreshState()
    LaunchedEffect(state.isRefreshing) {
        if (state.isRefreshing) {
            tabOrderViewModel.getOrdersByState(OrderStateCode.TODO)
            state.endRefresh()
        }
    }

    when (ordersState) {
        TabOrderViewModel.OrderState.Failure -> {}
        TabOrderViewModel.OrderState.Loading -> HandleShowLoading()
        is TabOrderViewModel.OrderState.Success -> {
            Box(Modifier.nestedScroll(state.nestedScrollConnection)) {
                OrdersComponent(orders = (ordersState as TabOrderViewModel.OrderState.Success).data, onClick = { navigateToOrderDetail(it) }, onLongClick = { navigateToOrderDetailAdmin(it) })
                PullToRefreshContainer(
                    modifier = Modifier.align(Alignment.TopCenter),
                    state = state,
                    containerColor = UIKitColorTheme.orange500,
                    contentColor = UIKitColorTheme.light01
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        tabOrderViewModel.getOrdersByState(OrderStateCode.TODO)
    }
}
