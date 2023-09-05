package com.afoxplus.yalistoadmin.ui.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.ui.orders.components.InfoBusinessComponent
import com.afoxplus.yalistoadmin.ui.orders.components.OrdersComponent

@Composable
fun HomeScreen(
    viewModel: OrdersStatusViewModel = hiltViewModel(),
    navigateTo: () -> Unit
) {
    val restaurant = RestaurantEntity(
        image = "https://cdn.designcrowd.com/blog/2016/January/fine-dining-and-restaurant-logos/4_450.png",
        name = "ChikenCoffee",
        description = "Establecimiento"
    )

    val orders by viewModel.order.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.getStatus()
    }

    Column {
        InfoBusinessComponent(restaurantEntity = restaurant)
        OrdersComponent(orders)
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen() {}
}

data class RestaurantEntity(
    val image: String,
    val name: String,
    val description: String
)
