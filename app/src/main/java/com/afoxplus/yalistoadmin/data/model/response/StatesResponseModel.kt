package com.afoxplus.yalistoadmin.data.model.response

import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import com.google.gson.annotations.SerializedName

data class StatesResponseModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
)

fun StatesResponseModel.toEntity(): StatesEntity {
    return StatesEntity(
        id = id,
        code = code,
        name = name
    )
}