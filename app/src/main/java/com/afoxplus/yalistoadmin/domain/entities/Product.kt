package com.afoxplus.yalistoadmin.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val productId: String,
    val title: String,
    val quantity: Int,
    val description: String?,
    val productType: String?,
    val unitPrice: String?,
    val subTotal: String?,
    val subDetail: List<Product>
) : Parcelable
