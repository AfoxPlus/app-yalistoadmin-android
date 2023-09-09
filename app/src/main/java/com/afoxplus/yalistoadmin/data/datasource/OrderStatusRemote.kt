package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.usecase.params.RestaurantParams

fun interface OrderStatusRemote {
    suspend fun getStatus(params: RestaurantParams): ResultState<List<Order>>
}
