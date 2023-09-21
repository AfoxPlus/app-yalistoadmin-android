package com.afoxplus.yalistoadmin.ui.screens.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.Restaurant
import com.afoxplus.yalistoadmin.ui.screens.orders.components.InfoBusinessComponent
import com.afoxplus.yalistoadmin.ui.screens.orders.components.OrdersComponent

@Composable
fun OrderScreen(
    viewModel: OrdersStatusViewModel = hiltViewModel(),
    navigateTo: (Order) -> Unit
) {
    val auth by viewModel.auth.collectAsStateWithLifecycle()
    val orders by viewModel.order.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.getAuth()
    }

    LaunchedEffect(key1 = auth) {
        if (auth.code != "") {
            viewModel.getStatus(auth.code)
        }
    }

    Column {
        InfoBusinessComponent(
            restaurant = Restaurant(
                image = auth.urlImageLogo,
                name = auth.name,
                description = stringResource(id = R.string.order_restaurant_description)
            )
        )
        OrdersComponent(orders = orders, onClick = { navigateTo(it) })
    }
}
