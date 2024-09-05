package com.afoxplus.yalistoadmin.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val productId: String,
    val imageUrl: String? = null,
    var showInApp: Boolean = false,
    val title: String,
    val quantity: Int,
    val notes: String?,
    val description: String?,
    val productType: String?,
    val unitPrice: String?,
    val subTotal: String?,
    val subDetail: List<Product> = arrayListOf()
) : Parcelable {
    fun isMenu(): Boolean = productType == "Menu"
}
