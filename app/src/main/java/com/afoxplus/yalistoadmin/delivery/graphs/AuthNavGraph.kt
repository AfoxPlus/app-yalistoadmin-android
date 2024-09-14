package com.afoxplus.yalistoadmin.delivery.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.afoxplus.yalistoadmin.delivery.screens.LoginScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.Authentication.route,
        startDestination = AuthScreenRouter.Login.route
    ) {
        composable(route = AuthScreenRouter.Login.route) {
            LoginScreen(navigateTo = {
                navController.popBackStack()
                navController.navigate(Graph.Home.route)
            })
        }
    }
}

sealed class AuthScreenRouter(val route: String) {
    object Login : AuthScreenRouter(route = "LOGIN")
    /*object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")*/
}
