package com.afoxplus.yalistoadmin.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.yalistoadmin.ui.home.components.InfoBusinessComponent
import com.afoxplus.yalistoadmin.ui.home.components.OrdersComponent

@Composable
fun HomeScreen(
    navigateTo: () -> Unit
) {
    val restaurant = RestaurantEntity(
        image = "https://cdn.designcrowd.com/blog/2016/January/fine-dining-and-restaurant-logos/4_450.png",
        name = "ChikenCoffee",
        description = "Establecimiento"
    )
    Column {
        InfoBusinessComponent(restaurantEntity = restaurant)
        OrdersComponent()
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
