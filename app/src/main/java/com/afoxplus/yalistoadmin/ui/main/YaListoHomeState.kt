package com.afoxplus.yalistoadmin.ui.main

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.afoxplus.yalistoadmin.ui.extensions.navigateSingleTopTo
import com.afoxplus.yalistoadmin.ui.screens.home.BottomBarHomeRouter
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberYaListoHomeState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    startDestination: BottomBarHomeRouter = BottomBarHomeRouter.Orders
) = remember(
    snackbarHostState,
    coroutineScope,
    navController,
    startDestination
) {
    YaListoHomeState(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        navController = navController,
        startDestination = startDestination
    )
}

@Stable
class YaListoHomeState(
    val snackbarHostState: SnackbarHostState,
    val coroutineScope: CoroutineScope,
    val navController: NavHostController,
    val startDestination: BottomBarHomeRouter
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: BottomBarHomeRouter
        @Composable get() {
            bottomBarHomeRouters.firstOrNull { it.route == currentDestination?.route }
                ?.let { mCurrentTopLevelDestination = it }
            return mCurrentTopLevelDestination
        }

    val shouldShowBar: Boolean
        @Composable get() = currentDestination?.route == currentTopLevelDestination.route

    val bottomBarHomeRouters = BottomBarHomeRouter.values()

    private var mCurrentTopLevelDestination by mutableStateOf(startDestination)

    fun onBackClick() = navController.popBackStack()
    fun navigate(destination: BottomBarHomeRouter) {
        navController.navigateSingleTopTo(destination.route)
    }
}
