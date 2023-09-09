package com.afoxplus.yalistoadmin.data.datasource.remote.model.response

import com.afoxplus.yalistoadmin.domain.entities.OrderType

data class OrderTypeResponseModel(
    val code: String,
    val title: String,
    val description: String?
)

fun OrderTypeResponseModel.toEntity(): OrderType {
    return OrderType(
        code = code,
        title = title,
        description = description ?: ""
    )
}
