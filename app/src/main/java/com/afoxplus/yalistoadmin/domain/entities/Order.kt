package com.afoxplus.yalistoadmin.domain.entities

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val id: String,
    val number: String,
    val date: String,
    val state: String,
    val stateCode: String,
    val restaurant: String,
    val orderType: OrderType,
    val total: String,
    val paymentMethod: String,
    val client: Client,
    val detail: List<Product>
) : Parcelable

val OrderNavType = object : NavType<Order>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Order? {
        return bundle.getParcelable(key) as Order?
    }

    override fun parseValue(value: String): Order {
        return Gson().fromJson(value, Order::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Order) {
        bundle.putParcelable(key, value)
    }
}
