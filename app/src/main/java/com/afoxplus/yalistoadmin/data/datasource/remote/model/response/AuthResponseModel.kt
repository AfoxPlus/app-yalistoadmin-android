package com.afoxplus.yalistoadmin.data.datasource.remote.model.response

import com.afoxplus.yalistoadmin.domain.entities.Auth
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

fun AuthResponseModel.toEntity(): Auth {
    return Auth(
        code = code,
        urlImageLogo = urlImageLogo,
        key = key,
        name = name
    )
}
