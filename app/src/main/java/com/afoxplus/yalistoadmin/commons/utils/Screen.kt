package com.afoxplus.yalistoadmin.commons.utils

import android.net.Uri
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.google.gson.Gson

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object HomeScreen : Screen("home_screen")
    object OrderScreen : Screen("order_screen/{${NavArgs.Order.key}}") {
        fun createRoute(order: Order) = "order_screen/${Uri.encode(Gson().toJson(order))}"
    }
}

enum class NavArgs(val key: String) {
    Order("order")
}
