package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.repository.OrderStatusRepository
import com.afoxplus.yalistoadmin.domain.usecase.params.RestaurantParams
import javax.inject.Inject

class GetOrderStatusUseCase @Inject constructor(
    private val repository: OrderStatusRepository
) {
    suspend fun getStatus(params: RestaurantParams): ResultState<List<Order>> {
        return repository.getStatus(params)
    }
}
