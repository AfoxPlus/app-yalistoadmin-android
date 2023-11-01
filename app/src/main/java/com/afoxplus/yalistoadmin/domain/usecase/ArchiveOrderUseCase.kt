package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.repository.OrderRepository
import javax.inject.Inject

class ArchiveOrderUseCase @Inject constructor(private val orderRepository: OrderRepository) {
    suspend fun archiveOrder(order: Order): ResultState<Unit> {
        return orderRepository.archiveOrder(order)
    }
}
