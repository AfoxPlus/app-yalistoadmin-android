package com.afoxplus.yalistoadmin.data.datasource.remote.model.request

import com.google.gson.annotations.SerializedName

data class ProductUpdateRequestModel(
    @SerializedName("code") val productCode: String,
    @SerializedName("showInApp") val isShowInApp: Boolean
)
