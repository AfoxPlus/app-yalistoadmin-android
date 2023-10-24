package com.afoxplus.yalistoadmin.domain.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.usecase.params.RestaurantParams

interface OrderStatusRepository {
    suspend fun getStatus(params: RestaurantParams): ResultState<List<Order>>
    suspend fun updateState(order: Order, state: String): ResultState<Order>
}
