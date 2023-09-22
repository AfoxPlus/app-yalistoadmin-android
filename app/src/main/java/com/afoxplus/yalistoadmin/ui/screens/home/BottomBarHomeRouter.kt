package com.afoxplus.yalistoadmin.ui.screens.home

import androidx.annotation.DrawableRes
import com.afoxplus.yalistoadmin.R

enum class BottomBarHomeRouter(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    Orders(
        route = "ORDERS",
        title = "Pedidos",
        icon = R.drawable.ic_order_item
    ),
    Products(
        route = "PRODUCTS",
        title = "Productos",
        icon = R.drawable.ic_products_item
    ),
    Sales(
        route = "SALES",
        title = "Ventas",
        icon = R.drawable.ic_sales_item
    );
}
