package com.afoxplus.yalistoadmin.commons.utils

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object HomeScreen : Screen("home_screen")
    object OrderScreen : Screen("order_screen")
}
