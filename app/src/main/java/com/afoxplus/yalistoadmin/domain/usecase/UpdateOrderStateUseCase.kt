package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.OrderStateCode
import com.afoxplus.yalistoadmin.domain.repository.OrderStatusRepository
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import javax.inject.Inject

class UpdateOrderStateUseCase @Inject constructor(
    private val orderStatusRepository: OrderStatusRepository,
    private val statesRepository: StatesRepository
) {
    suspend operator fun invoke(order: Order, state: String): ResultState<Order> {
        return orderStatusRepository.updateState(order, state)
    }

    suspend operator fun invoke(order: Order, orderStateCode: OrderStateCode): ResultState<Order> {
        return when (val state = statesRepository.getStateByCode(orderStateCode.name)) {
            is ResultState.Error -> ResultState.Error(Exception("State not found"))
            is ResultState.Success -> {
                orderStatusRepository.updateState(order, state.data.id)
            }
        }
    }
}
