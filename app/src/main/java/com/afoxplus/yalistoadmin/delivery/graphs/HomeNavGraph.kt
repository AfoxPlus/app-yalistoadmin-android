package com.afoxplus.yalistoadmin.delivery.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.afoxplus.yalistoadmin.delivery.routers.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.delivery.screens.OrderDetailAdminScreen
import com.afoxplus.yalistoadmin.delivery.screens.OrderDetailScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.KitchenHomeScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.OrdersHomeScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.ProductScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.TablesScreen
import com.afoxplus.yalistoadmin.domain.entities.OrderNavType

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = Graph.Home.route,
        startDestination = BottomBarHomeRouter.Orders.route
    ) {
        composable(route = BottomBarHomeRouter.Orders.route) {
            OrdersHomeScreen(
                navigateToOrderDetail = { order ->
                    navController.navigate(Graph.OrderDetails.createRoute(order = order))
                },
                navigateToOrderDetailAdmin = { order ->
                    navController.navigate(Graph.OrderDetailsADM.createRoute(order = order))
                }
            )
        }

        composable(route = BottomBarHomeRouter.Kitchen.route) {
            KitchenHomeScreen(
                navigateToOrderDetail = { order ->
                    navController.navigate(Graph.OrderDetails.createRoute(order = order))
                },
                navigateToOrderDetailAdmin = { order ->
                    navController.navigate(Graph.OrderDetailsADM.createRoute(order = order))
                }
            )
        }

        composable(route = BottomBarHomeRouter.Tables.route) {
            TablesScreen()
        }

        composable(route = BottomBarHomeRouter.Products.route) {
            ProductScreen()
        }

        orderDetailNavGraph(navController)
        orderDetailAdmNavGraph(navController)
    }
}

fun NavGraphBuilder.orderDetailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.OrderDetails.route,
        startDestination = HomeScreensRouter.OrderDetail.route,
        arguments = listOf(navArgument(NavArgs.Order.key) { type = OrderNavType })
    ) {
        composable(route = HomeScreensRouter.OrderDetail.route) {
            OrderDetailScreen(navigateBack = {
                navController.popBackStack()
            })
        }
    }
}

fun NavGraphBuilder.orderDetailAdmNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.OrderDetailsADM.route,
        startDestination = HomeScreensRouter.OrderDetailADM.route,
        arguments = listOf(navArgument(NavArgs.Order.key) { type = OrderNavType })
    ) {
        composable(route = HomeScreensRouter.OrderDetailADM.route) {
            OrderDetailAdminScreen(navigateBack = {
                navController.popBackStack()
            })
        }
    }
}

sealed class HomeScreensRouter(val route: String) {
    data object OrderDetail : HomeScreensRouter(route = "ORDER_DETAIL")
    data object OrderDetailADM : HomeScreensRouter(route = "ORDER_DETAIL_ADM")
}
