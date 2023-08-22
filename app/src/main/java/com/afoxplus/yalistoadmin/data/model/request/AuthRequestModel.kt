package com.afoxplus.yalistoadmin.data.model.request

import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import com.google.gson.annotations.SerializedName

data class AuthRequestModel(
    @SerializedName("key")
    val key: String
)

fun AuthParams.toRequest(): AuthRequestModel {
    return AuthRequestModel(
        key = key
    )
}
