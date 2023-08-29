package com.afoxplus.yalistoadmin.data.datasource.remote.model.response

import com.afoxplus.yalistoadmin.domain.entities.States
import com.google.gson.annotations.SerializedName

data class StatesResponseModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
)

fun StatesResponseModel.toEntity(): States {
    return States(
        id = id,
        code = code,
        name = name
    )
}
