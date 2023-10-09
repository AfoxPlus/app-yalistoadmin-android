package com.afoxplus.yalistoadmin.ui.graphs

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.ui.screens.home.HomeScreen
import com.afoxplus.yalistoadmin.ui.screens.splash.CheckInScreen
import com.google.gson.Gson

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.Root.route,
        startDestination = Graph.CheckIn.route
    ) {
        composable(route = Graph.CheckIn.route) {
            CheckInScreen(
                onSessionActive = {
                    navController.popBackStack()
                    navController.navigate(Graph.Home.route)
                },
                onSessionInactive = {
                    navController.popBackStack()
                    navController.navigate(Graph.Authentication.route)
                }
            )
        }
        authNavGraph(navController = navController)
        composable(route = Graph.Home.route) { HomeScreen() }
    }
}

sealed class Graph(val route: String) {
    object Root : Graph("root_graph")
    object CheckIn : Graph("check_in_graph")
    object Authentication : Graph("auth_graph")
    object Home : Graph("home_graph")
    object OrderDetails : Graph("order_screen/{${NavArgs.Order.key}}") {
        fun createRoute(order: Order) = "order_screen/${Uri.encode(Gson().toJson(order))}"
    }
}

enum class NavArgs(val key: String) {
    Order("order")
}
