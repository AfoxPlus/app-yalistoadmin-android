package com.afoxplus.yalistoadmin.data.datasource.remote.model.response

import com.afoxplus.yalistoadmin.domain.entities.Product
import com.google.gson.annotations.SerializedName

data class ProductResponseModel(
    val productId: String,
    val title: String,
    val imageUrl: String? = null,
    val showInApp: Boolean = true,
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

data class ProductTypeModel(val code: String, val name: String)
data class ProductSearchResponseModel(
    @SerializedName("code") val productId: String,
    @SerializedName("name") val title: String,
    val imageUrl: String? = null,
    val showInApp: Boolean = true,
    @SerializedName("stock") val quantity: Int,
    val description: String?,
    val productType: ProductTypeModel,
    @SerializedName("price") val unitPrice: String?
)

fun ProductSearchResponseModel.toEntity(): Product {
    return Product(
        imageUrl = imageUrl,
        productId = productId,
        title = title,
        quantity = quantity,
        description = description,
        productType = productType.name,
        unitPrice = unitPrice,
        subTotal = unitPrice,
        showInApp = showInApp,
        subDetail = emptyList()
    )
}
