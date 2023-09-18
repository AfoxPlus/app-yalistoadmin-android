package com.afoxplus.yalistoadmin.data.datasource.remote.model.request

import com.google.gson.annotations.SerializedName

data class OrderStateRequestModel(
    @SerializedName("order_id") val orderId: String,
    @SerializedName("order_state") val orderState: String
)