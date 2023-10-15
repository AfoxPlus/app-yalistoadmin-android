package com.afoxplus.yalistoadmin.ui.screens.order_status

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.usecase.UpdateOrderStateUseCase
import com.afoxplus.yalistoadmin.ui.graphs.NavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val orderStateUseCase: UpdateOrderStateUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _orderState: MutableStateFlow<Order?> = MutableStateFlow(null)
    val orderState: StateFlow<Order?> = _orderState.asStateFlow()

    private val order: Order? = savedStateHandle[NavArgs.Order.key]

    init {
        _orderState.value = order
    }

    fun sendOrderState(state: String) {
        viewModelScope.launch(dispatcher.getIODispatcher()) {
            order?.let {
                try {
                    orderStateUseCase.updateState(it, state)
                } catch (ex: Exception) {
                }
            }
        }
    }
}
