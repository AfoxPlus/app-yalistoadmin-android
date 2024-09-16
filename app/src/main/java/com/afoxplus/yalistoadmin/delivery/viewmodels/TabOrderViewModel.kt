package com.afoxplus.yalistoadmin.delivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.OrderStateCode
import com.afoxplus.yalistoadmin.domain.usecase.GetOrderStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TabOrderViewModel @Inject constructor(
    private val getOrderStatusUseCase: GetOrderStatusUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val mOrdersState: MutableStateFlow<OrderState> = MutableStateFlow(OrderState.Loading)
    val ordersState: StateFlow<OrderState> = mOrdersState.asStateFlow()

    fun getOrdersByState(orderState: OrderStateCode): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                when (val result = getOrderStatusUseCase(orderState)) {
                    is ResultState.Error -> {
                        mOrdersState.value = OrderState.Failure
                    }

                    is ResultState.Success -> {
                        mOrdersState.value = OrderState.Success(result.data)
                    }
                }
            } catch (e: Exception) {
                mOrdersState.value = OrderState.Failure
            }
        }
    }

    fun getOrdersByStateId(orderStateId: String): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                when (val result = getOrderStatusUseCase(orderStateId)) {
                    is ResultState.Error -> {
                        mOrdersState.value = OrderState.Failure
                    }

                    is ResultState.Success -> {
                        mOrdersState.value = OrderState.Success(result.data)
                    }
                }
            } catch (e: Exception) {
                mOrdersState.value = OrderState.Failure
            }
        }
    }

    sealed interface OrderState {
        object Loading : OrderState
        data class Success(val data: List<Order>) : OrderState
        object Failure : OrderState
    }
}
