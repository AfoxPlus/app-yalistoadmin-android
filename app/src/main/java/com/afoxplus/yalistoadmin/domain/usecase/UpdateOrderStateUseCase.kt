package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.repository.OrderStatusRepository
import javax.inject.Inject

class UpdateOrderStateUseCase @Inject constructor(private val orderStatusRepository: OrderStatusRepository) {
    suspend fun updateState(order: Order, state: String): ResultState<Order> {
        return orderStatusRepository.updateState(order, state)
    }
}
