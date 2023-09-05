package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.repository.OrderStatusRepository
import javax.inject.Inject

class GetOrderStatusUseCase @Inject constructor(
    private val repository: OrderStatusRepository
) {
    suspend fun getStatus(): ResultState<List<Order>> {
        return repository.getStatus()
    }
}
