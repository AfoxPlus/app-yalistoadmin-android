package com.afoxplus.yalistoadmin.data.datasource.remote.model.request

import com.afoxplus.yalistoadmin.domain.usecase.params.RestaurantParams

data class RestaurantRequestModel(
    val code: String,
    val stateId: String
)

fun RestaurantParams.toRequest(): RestaurantRequestModel {
    return RestaurantRequestModel(
        code = code,
        stateId = stateId
    )
}
