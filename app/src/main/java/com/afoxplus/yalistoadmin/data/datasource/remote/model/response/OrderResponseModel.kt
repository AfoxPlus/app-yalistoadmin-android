package com.afoxplus.yalistoadmin.data.datasource.remote.model.response

import com.afoxplus.yalistoadmin.domain.entities.Order
import com.google.gson.annotations.SerializedName

data class OrderResponseModel(
    val id: String,
    val number: String,
    val date: String,
    val state: String,
    @SerializedName("state_code")
    val stateCode: String,
    val restaurant: String,
    @SerializedName("order_type")
    val orderType: OrderTypeResponseModel,
    val total: String,
    val client: ClientResponseModel,
    val detail: List<ProductResponseModel> = arrayListOf()
)

fun OrderResponseModel.toEntity(): Order {
    return Order(
        id = id,
        number = number,
        date = date,
        state = state,
        stateCode = stateCode,
        restaurant = restaurant,
        orderType = orderType.toEntity(),
        total = total,
        client = client.toEntity(),
        detail = detail.map { it.toEntity() }
    )
}
