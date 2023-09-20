package com.afoxplus.yalistoadmin.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hardware
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Orders : BottomBarScreen(
        route = "ORDERS",
        title = "ORDERS",
        icon = Icons.Default.Home
    )

    object Products : BottomBarScreen(
        route = "PRODUCTS",
        title = "PRODUCTS",
        icon = Icons.Default.Hardware
    )

    object Sales : BottomBarScreen(
        route = "SALES",
        title = "SALES",
        icon = Icons.Default.Settings
    )
}
