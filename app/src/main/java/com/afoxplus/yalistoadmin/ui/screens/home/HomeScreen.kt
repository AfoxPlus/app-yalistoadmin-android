package com.afoxplus.yalistoadmin.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light05
import com.afoxplus.uikitcompose.ui.theme.Mulish
import com.afoxplus.uikitcompose.ui.theme.Orange01
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.entities.Restaurant
import com.afoxplus.yalistoadmin.ui.graphs.HomeNavGraph
import com.afoxplus.yalistoadmin.ui.screens.orders.OrdersStatusViewModel
import com.afoxplus.yalistoadmin.ui.screens.home.components.InfoBusinessComponent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    appState: YaListoHomeState = rememberYaListoHomeState(),
    viewModel: OrdersStatusViewModel = hiltViewModel()
) {
    val auth by viewModel.auth.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = appState.shouldShowBar
            ) {
                TopBarYaListo(auth)
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = appState.shouldShowBar) {
                BottomBarYaListo(
                    appState = appState,
                    onNavigateToDestination = appState::navigate
                )
            }
        },
        contentWindowInsets = WindowInsets(
            left = 0.dp,
            top = 0.dp,
            right = 0.dp,
            bottom = 0.dp
        )
    ) { innerPadding ->
        HomeNavGraph(
            navController = appState.navController,
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .consumeWindowInsets(paddingValues = innerPadding)
        )
    }
    LaunchedEffect(key1 = Unit) { viewModel.getAuth() }
}

@Composable
fun TopBarYaListo(auth: Auth) {
    InfoBusinessComponent(
        restaurant = Restaurant(
            image = auth.urlImageLogo,
            name = auth.name,
            description = stringResource(id = R.string.order_restaurant_description)
        )
    )
}

@Composable
fun BottomBarYaListo(
    modifier: Modifier = Modifier,
    appState: YaListoHomeState,
    onNavigateToDestination: (BottomBarHomeRouter) -> Unit
) {
    NavigationBar(modifier = modifier, containerColor = Light05) {
        appState.bottomBarHomeRouters.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = appState.currentDestination,
                onNavigateToDestination = onNavigateToDestination
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarHomeRouter,
    currentDestination: NavDestination?,
    onNavigateToDestination: (BottomBarHomeRouter) -> Unit
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

        onClick = { onNavigateToDestination(screen) }
    )
}
