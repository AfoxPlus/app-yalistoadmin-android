package com.afoxplus.yalistoadmin.domain.entities

import android.os.Parcelable
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
    val client: Client,
    val detail: List<Product>
) : Parcelable
