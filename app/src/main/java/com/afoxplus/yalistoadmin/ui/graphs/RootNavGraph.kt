package com.afoxplus.yalistoadmin.ui.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.afoxplus.yalistoadmin.ui.screens.home.HomeScreen
import com.afoxplus.yalistoadmin.ui.screens.splash.CheckInScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.CHECK_IN
    ) {
        composable(route = Graph.CHECK_IN) {
            CheckInScreen(
                onSessionActive = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onSessionInactive = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                }
            )
        }
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) { HomeScreen() }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val CHECK_IN = "check_in_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val HOME_DETAILS = "home_details_graph"
}
