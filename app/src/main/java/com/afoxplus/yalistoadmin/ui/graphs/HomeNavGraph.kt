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
import androidx.navigation.navArgument
import com.afoxplus.uikitcompose.ui.theme.Header03
import com.afoxplus.yalistoadmin.domain.entities.OrderNavType
import com.afoxplus.yalistoadmin.ui.extensions.navigateSingleTopTo
import com.afoxplus.yalistoadmin.ui.extensions.sharedViewModel
import com.afoxplus.yalistoadmin.ui.screens.details.OrderStatusScreen
import com.afoxplus.yalistoadmin.ui.screens.home.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.ui.screens.orders.OrderScreen
import com.afoxplus.yalistoadmin.ui.screens.orders.OrdersStatusViewModel
import com.afoxplus.yalistoadmin.ui.screens.products.ProductScreen

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
            // TODO: Replace with SalesScreen
            ScreenContent(name = BottomBarHomeRouter.Sales.title, onClick = {})
        }
        homeDetailsNavGraph(navController)
    }
}

fun NavGraphBuilder.homeDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.OrderDetails.route,
        startDestination = HomeDetailsScreenRouter.OrderDetail.route,
        arguments = listOf(navArgument(NavArgs.Order.key) { type = OrderNavType })
    ) {
        composable(route = HomeDetailsScreenRouter.OrderDetail.route) {
            OrderStatusScreen(navigateBack = {
                navController.popBackStack()
            })
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
