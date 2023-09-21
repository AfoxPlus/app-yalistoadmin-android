package com.afoxplus.yalistoadmin.ui.screens.home

import androidx.annotation.DrawableRes
import com.afoxplus.yalistoadmin.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Orders : BottomBarScreen(
        route = "ORDERS",
        title = "Pedidos",
        icon = R.drawable.ic_order_item
    )

    object Products : BottomBarScreen(
        route = "PRODUCTS",
        title = "Productos",
        icon = R.drawable.ic_products_item
    )

    object Sales : BottomBarScreen(
        route = "SALES",
        title = "Ventas",
        icon = R.drawable.ic_sales_item
    )
}
