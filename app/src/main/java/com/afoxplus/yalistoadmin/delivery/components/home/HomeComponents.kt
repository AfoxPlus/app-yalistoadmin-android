package com.afoxplus.yalistoadmin.delivery.components.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.delivery.components.restaurant.InfoBusinessComponent
import com.afoxplus.yalistoadmin.delivery.routers.BottomBarHomeRouter
import com.afoxplus.yalistoadmin.delivery.screens.home.YaListoHomeState
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.entities.Restaurant

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
    NavigationBar(modifier = modifier, containerColor = UIKitTheme.colors.blueGray25) {
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
                style = UIKitTheme.typography.paragraph02
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
            selectedIconColor = UIKitTheme.colors.light01,
            unselectedIconColor = UIKitTheme.colors.secondaryColor,
            selectedTextColor = UIKitTheme.colors.primaryColor,
            unselectedTextColor = UIKitTheme.colors.secondaryColor,
            indicatorColor = UIKitTheme.colors.primaryColor
        ),

        onClick = { onNavigateToDestination(screen) }
    )
}
