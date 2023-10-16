package com.afoxplus.yalistoadmin.ui.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.afoxplus.yalistoadmin.commons.extensions.sharedViewModel
import com.afoxplus.yalistoadmin.domain.entities.OrderNavType
import com.afoxplus.yalistoadmin.ui.screens.home.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.ui.screens.home.orders.OrdersHomeScreen
import com.afoxplus.yalistoadmin.ui.screens.home.sales.SalesHomeScreen
import com.afoxplus.yalistoadmin.ui.screens.orders.OrdersStatusViewModel
import com.afoxplus.yalistoadmin.ui.screens.products.ProductScreen
import com.afoxplus.yalistoadmin.ui.screens.status.OrderStatusScreen

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = Graph.Home.route,
        startDestination = BottomBarHomeRouter.Orders.route
    ) {
        composable(route = BottomBarHomeRouter.Orders.route) {
            val viewModel = it.sharedViewModel<OrdersStatusViewModel>(navController = navController)
            OrdersHomeScreen(viewModel = viewModel, navigateTo = { order ->
                navController.navigate(Graph.OrderDetails.createRoute(order = order))
            })
        }
        composable(route = BottomBarHomeRouter.Products.route) {
            ProductScreen()
        }
        composable(route = BottomBarHomeRouter.Sales.route) {
            val viewModel = it.sharedViewModel<OrdersStatusViewModel>(navController = navController)
            SalesHomeScreen(viewModel = viewModel, navigateTo = { order ->
                navController.navigate(Graph.OrderDetails.createRoute(order = order))
            })
        }
        homeScreensNavGraph(navController)
    }
}

fun NavGraphBuilder.homeScreensNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.OrderDetails.route,
        startDestination = HomeScreensRouter.OrderDetail.route,
        arguments = listOf(navArgument(NavArgs.Order.key) { type = OrderNavType })
    ) {
        composable(route = HomeScreensRouter.OrderDetail.route) {
            OrderStatusScreen(navigateBack = {
                navController.popBackStack()
            })
        }
    }
}

sealed class HomeScreensRouter(val route: String) {
    object OrderDetail : HomeScreensRouter(route = "ORDER_DETAIL")
}
