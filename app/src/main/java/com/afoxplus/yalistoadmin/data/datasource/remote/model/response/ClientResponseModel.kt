package com.afoxplus.yalistoadmin.data.datasource.remote.model.response

import com.afoxplus.yalistoadmin.domain.entities.Client
import com.google.gson.annotations.SerializedName

data class ClientResponseModel(
    val name: String,
    val cel: String?,
    @SerializedName("address_reference")
    val addressReference: String?
)

fun ClientResponseModel.toEntity(): Client {
    return Client(
        name = name,
        cel = cel ?: "",
        addressReference = addressReference ?: ""
    )
}
