package com.afoxplus.yalistoadmin.delivery.graphs

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.afoxplus.yalistoadmin.delivery.screens.CheckInScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.HomeScreen
import com.afoxplus.yalistoadmin.domain.entities.Order
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
    data object Root : Graph("root_graph")
    data object CheckIn : Graph("check_in_graph")
    data object Authentication : Graph("auth_graph")
    data object Home : Graph("home_graph")
    data object OrderDetails : Graph("order_screen/{${NavArgs.Order.key}}") {
        fun createRoute(order: Order) = "order_screen/${Uri.encode(Gson().toJson(order))}"
    }
    data object OrderDetailsADM : Graph("order_screen_adm/{${NavArgs.Order.key}}") {
        fun createRoute(order: Order) = "order_screen_adm/${Uri.encode(Gson().toJson(order))}"
    }
}

enum class NavArgs(val key: String) {
    Order("order")
}
