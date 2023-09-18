package com.afoxplus.yalistoadmin.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.NavArgs
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.usecase.UpdateOrderStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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
