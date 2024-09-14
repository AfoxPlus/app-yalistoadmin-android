package com.afoxplus.yalistoadmin.delivery.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.afoxplus.yalistoadmin.cross.extensions.sharedViewModel
import com.afoxplus.yalistoadmin.delivery.routers.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.delivery.screens.OrderStatusScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.KitchenHomeScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.OrdersHomeScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.ProductScreen
import com.afoxplus.yalistoadmin.delivery.screens.home.navbar.TablesScreen
import com.afoxplus.yalistoadmin.delivery.viewmodels.HomeViewModel
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
            val viewModel = it.sharedViewModel<HomeViewModel>(navController = navController)
            OrdersHomeScreen(
                route = BottomBarHomeRouter.Orders,
                viewModel = viewModel,
                navigateTo = { order ->
                    navController.navigate(Graph.OrderDetails.createRoute(order = order))
                }
            )
        }

        composable(route = BottomBarHomeRouter.Kitchen.route) {
            val viewModel = it.sharedViewModel<HomeViewModel>(navController = navController)
            KitchenHomeScreen(
                route = BottomBarHomeRouter.Kitchen,
                viewModel = viewModel,
                navigateTo = { order ->
                    navController.navigate(Graph.OrderDetails.createRoute(order = order))
                }
            )
        }

        composable(route = BottomBarHomeRouter.Tables.route) {
            TablesScreen()
        }

        composable(route = BottomBarHomeRouter.Products.route) {
            ProductScreen()
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
