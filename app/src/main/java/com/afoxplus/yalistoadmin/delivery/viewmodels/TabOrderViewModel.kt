package com.afoxplus.yalistoadmin.delivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.OrderStateCode
import com.afoxplus.yalistoadmin.domain.entities.States
import com.afoxplus.yalistoadmin.domain.usecase.GetOrderStatusUseCase
import com.afoxplus.yalistoadmin.domain.usecase.GetStatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TabOrderViewModel @Inject constructor(
    private val stateUseCase: GetStatesUseCase,
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
                mOrdersState.value = OrderState.Loading
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

    private val mStatesTabKitchen: MutableStateFlow<List<States>> by lazy { MutableStateFlow(emptyList()) }
    val statesTabKitchen = mStatesTabKitchen.asStateFlow()

    fun getKitchenOrderStates() = viewModelScope.launch(dispatcher.getIODispatcher()) {
        when (val statesResult = stateUseCase.getStates()) {
            is ResultState.Success -> {
                val states = mutableListOf<States>()
                statesResult.data.find { item -> item.code == OrderStateCode.PROGRESS.name }.also { item -> item?.let { states.add(it) } }
                statesResult.data.find { item -> item.code == OrderStateCode.DONE.name }.also { item -> item?.let { states.add(it) } }
                statesResult.data.find { item -> item.code == OrderStateCode.REJECTED.name }.also { item -> item?.let { states.add(it) } }
                mStatesTabKitchen.value = states
            }

            else -> {
                // Nothing
            }
        }
    }

    sealed interface OrderState {
        data object Loading : OrderState
        data class Success(val data: List<Order>) : OrderState
        data object Failure : OrderState
    }
}
