package com.afoxplus.yalistoadmin.ui.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.afoxplus.yalistoadmin.commons.extensions.navigateSingleTopTo
import com.afoxplus.yalistoadmin.commons.extensions.sharedViewModel
import com.afoxplus.yalistoadmin.domain.entities.OrderNavType
import com.afoxplus.yalistoadmin.ui.screens.details.OrderStatusScreen
import com.afoxplus.yalistoadmin.ui.screens.home.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.ui.screens.orders.OrderScreen
import com.afoxplus.yalistoadmin.ui.screens.orders.OrdersStatusViewModel
import com.afoxplus.yalistoadmin.ui.screens.products.ProductScreen
import com.afoxplus.yalistoadmin.ui.screens.sales.SalesScreen

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
            OrderScreen(viewModel = viewModel, navigateTo = { order ->
                navController.navigateSingleTopTo(Graph.OrderDetails.createRoute(order = order))
            })
        }
        composable(route = BottomBarHomeRouter.Products.route) {
            ProductScreen()
        }
        composable(route = BottomBarHomeRouter.Sales.route) {
            val viewModel = it.sharedViewModel<OrdersStatusViewModel>(navController = navController)
            SalesScreen(viewModel = viewModel, navigateTo = { order ->
                navController.navigateSingleTopTo(Graph.OrderDetails.createRoute(order = order))
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
