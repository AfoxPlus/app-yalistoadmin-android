package com.afoxplus.yalistoadmin.delivery.routers

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
    Kitchen(
        route = "KITCHEN",
        title = "Cocina",
        icon = R.drawable.ic_kitchen
    ),
    Tables(
        route = "TABLES",
        title = "Mesas",
        icon = R.drawable.ic_tables
    ),
    Products(
        route = "PRODUCTS",
        title = "Productos",
        icon = R.drawable.ic_products_item
    );
}
