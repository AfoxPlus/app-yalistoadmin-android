package com.afoxplus.yalistoadmin.ui.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.usecase.GetAuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.GetOrderStatusUseCase
import com.afoxplus.yalistoadmin.domain.usecase.params.RestaurantParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OrdersStatusViewModel @Inject constructor(
    private val getOrderStatusUseCase: GetOrderStatusUseCase,
    private val getAuthUseCase: GetAuthUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _order: MutableStateFlow<List<Order>> = MutableStateFlow(arrayListOf())
    val order: StateFlow<List<Order>> = _order.asStateFlow()

    private val _auth: MutableStateFlow<Auth> = MutableStateFlow(Auth("", "", "", ""))
    val auth: StateFlow<Auth> = _auth.asStateFlow()

    fun getAuth(): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            _auth.value = getAuthUseCase.authPreferences()
        }
    }

    fun getStatus(restaurantCode: String): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                when (val result = getOrderStatusUseCase.getStatus(RestaurantParams(code = restaurantCode))) {
                    is ResultState.Error -> {
                        _isLoading.value = false
                    }

                    is ResultState.Success -> {
                        _order.value = result.data
                    }
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }
}
