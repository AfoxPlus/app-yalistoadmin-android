package com.afoxplus.yalistoadmin.data.model.response

import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.google.gson.annotations.SerializedName

data class AuthResponseModel(
    @SerializedName("code")
    val code: String,
    @SerializedName("urlImageLogo")
    val urlImageLogo: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String
)

fun AuthResponseModel.toEntity(): AuthEntity {
    return AuthEntity(
        code = code,
        urlImageLogo = urlImageLogo,
        key = key,
        name = name
    )
}