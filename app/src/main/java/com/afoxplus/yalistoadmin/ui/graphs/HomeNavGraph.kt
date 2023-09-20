package com.afoxplus.yalistoadmin.ui.graphs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.afoxplus.uikitcompose.ui.theme.Header03
import com.afoxplus.yalistoadmin.ui.screens.details.OrderStatusScreen
import com.afoxplus.yalistoadmin.ui.screens.home.BottomBarScreen
import com.afoxplus.yalistoadmin.ui.screens.orders.OrderScreen

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Orders.route
    ) {
        composable(route = BottomBarScreen.Orders.route) {
            OrderScreen(navigateTo = {
                navController.navigate(Graph.HOME_DETAILS)
            })
        }

        composable(route = BottomBarScreen.Products.route) {
            // TODO: Replace with ProductScreen
            ScreenContent(name = BottomBarScreen.Products.title, onClick = {})
        }
        composable(route = BottomBarScreen.Sales.route) {
            // TODO: Replace with SalesScreen
            ScreenContent(name = BottomBarScreen.Products.title, onClick = {})
        }
        homeDetailsNavGraph(navController)
    }
}

fun NavGraphBuilder.homeDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOME_DETAILS,
        startDestination = HomeDetailsScreenRouter.OrderDetail.route
    ) {
        composable(route = HomeDetailsScreenRouter.OrderDetail.route) {
            OrderStatusScreen()
        }
    }
}

sealed class HomeDetailsScreenRouter(val route: String) {
    object OrderDetail : HomeDetailsScreenRouter(route = "ORDER_DETAIL")
}

// Temporal
@Composable
fun ScreenContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = name,
            style = Header03
        )
    }
}
