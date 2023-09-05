package com.afoxplus.yalistoadmin.data.datasource.remote.model.response

import com.afoxplus.yalistoadmin.domain.entities.Product

data class ProductResponseModel(
    val productId: String,
    val title: String,
    val quantity: Int,
    val description: String?,
    val productType: String?,
    val unitPrice: String?,
    val subTotal: String?,
    val subDetail: List<ProductResponseModel>?
)

fun ProductResponseModel.toEntity(): Product {
    return Product(
        productId = productId,
        title = title,
        quantity = quantity,
        description = description,
        productType = productType,
        unitPrice = unitPrice,
        subTotal = subTotal,
        subDetail = subDetail?.map { it.toEntity() } ?: arrayListOf()
    )
}
