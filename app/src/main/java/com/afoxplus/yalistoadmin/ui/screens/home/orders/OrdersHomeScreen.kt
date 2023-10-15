package com.afoxplus.yalistoadmin.ui.screens.home.orders

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.components.TabItemYaListo
import com.afoxplus.uikitcompose.ui.components.TapPagerYaListoComponent
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.ui.screens.orders.OrderScreen
import com.afoxplus.yalistoadmin.ui.screens.orders.OrdersStatusViewModel

@Composable
fun OrdersHomeScreen(
    viewModel: OrdersStatusViewModel,
    navigateTo: (Order) -> Unit
) {
    val tabItems = listOf(
        TabItemYaListo(
            title = stringResource(id = R.string.order_pending_state),
            screen = { OrderScreen(viewModel = viewModel, navigateTo = navigateTo) }
        ),
        TabItemYaListo(
            title = stringResource(id = R.string.order_process_state),
            screen = { OrderScreen(viewModel = viewModel, navigateTo = navigateTo) }
        )
    )

    TapPagerYaListoComponent(
        modifier = Modifier.padding(horizontal = 16.dp),
        tabItems = tabItems
    ) { index ->
        tabItems[index].screen()
    }
}
