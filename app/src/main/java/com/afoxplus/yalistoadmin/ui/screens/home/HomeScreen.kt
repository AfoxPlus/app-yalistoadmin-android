package com.afoxplus.yalistoadmin.ui.screens.home

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Mulish
import com.afoxplus.uikitcompose.ui.theme.Orange01
import com.afoxplus.uikitcompose.ui.theme.Yellow02
import com.afoxplus.yalistoadmin.ui.extensions.navigateSingleTopTo
import com.afoxplus.yalistoadmin.ui.graphs.HomeNavGraph

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        contentWindowInsets = WindowInsets(
            left = 0.dp,
            top = 0.dp,
            right = 0.dp,
            bottom = 0.dp
        )
    ) { innerPadding ->
        HomeNavGraph(
            navController = navController,
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .consumeWindowInsets(paddingValues = innerPadding)
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val screens = listOf(
        BottomBarScreen.Orders,
        BottomBarScreen.Products,
        BottomBarScreen.Sales
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(modifier = modifier, containerColor = Yellow02) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(
                text = screen.title,
                fontFamily = Mulish,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Light01,
            unselectedIconColor = Dark01,
            selectedTextColor = Orange01,
            unselectedTextColor = Dark01,
            indicatorColor = Orange01
        ),

        onClick = {
            navController.navigateSingleTopTo(screen.route)
        }
    )
}
