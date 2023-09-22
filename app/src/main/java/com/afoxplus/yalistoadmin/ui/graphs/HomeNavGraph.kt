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
import com.afoxplus.yalistoadmin.ui.extensions.navigateSingleTopTo
import com.afoxplus.yalistoadmin.ui.extensions.sharedViewModel
import com.afoxplus.yalistoadmin.ui.screens.details.OrderStatusScreen
import com.afoxplus.yalistoadmin.ui.screens.home.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.ui.screens.orders.OrderScreen
import com.afoxplus.yalistoadmin.ui.screens.orders.OrdersStatusViewModel

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarHomeRouter.Orders.route
    ) {
        composable(route = BottomBarHomeRouter.Orders.route) {
            val viewModel = it.sharedViewModel<OrdersStatusViewModel>(navController = navController)
            OrderScreen(viewModel = viewModel, navigateTo = {
                navController.navigateSingleTopTo(Graph.HOME_DETAILS)
            })
        }

        composable(route = BottomBarHomeRouter.Products.route) {
            // TODO: Replace with ProductScreen
            ScreenContent(name = BottomBarHomeRouter.Products.title, onClick = {})
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
