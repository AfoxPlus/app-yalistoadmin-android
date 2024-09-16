package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.OrderStateCode
import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import com.afoxplus.yalistoadmin.domain.repository.OrderStatusRepository
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import com.afoxplus.yalistoadmin.domain.usecase.params.RestaurantParams
import javax.inject.Inject

class GetOrderStatusUseCase @Inject constructor(
    private val orderRepository: OrderStatusRepository,
    private val authRepository: AuthRepository,
    private val statesRepository: StatesRepository
) {
    suspend operator fun invoke(orderStateId: String): ResultState<List<Order>> {
        val auth = authRepository.getAuth()
        return if (auth.code.isNotEmpty()) {
            orderRepository.getStatus(RestaurantParams(code = auth.code, stateId = orderStateId))
        } else {
            ResultState.Error(Exception(ERROR_RESTAURANT_CODE_EMPTY))
        }
    }

    suspend operator fun invoke(orderState: OrderStateCode): ResultState<List<Order>> {
        val auth = authRepository.getAuth()
        return if (auth.code.isNotEmpty()) {
            when (val state = statesRepository.getStateByCode(orderState.name)) {
                is ResultState.Error -> {
                    ResultState.Error(state.exception)
                }

                is ResultState.Success -> {
                    orderRepository.getStatus(RestaurantParams(code = auth.code, stateId = state.data.id))
                }
            }
        } else {
            ResultState.Error(Exception(ERROR_RESTAURANT_CODE_EMPTY))
        }
    }

    companion object {
        const val ERROR_RESTAURANT_CODE_EMPTY = "Restaurant code is empty"
    }
}
