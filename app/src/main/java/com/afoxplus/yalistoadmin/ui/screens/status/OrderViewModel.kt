package com.afoxplus.yalistoadmin.ui.screens.status

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.constants.ConstantsDomain
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.usecase.GetStatesUseCase
import com.afoxplus.yalistoadmin.domain.usecase.UpdateOrderStateUseCase
import com.afoxplus.yalistoadmin.ui.graphs.NavArgs
import com.afoxplus.yalistoadmin.ui.screens.status.vo.StatesVO
import com.afoxplus.yalistoadmin.ui.screens.status.vo.toVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val orderStateUseCase: UpdateOrderStateUseCase,
    private val getStatesUseCase: GetStatesUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _orderState: MutableStateFlow<Order?> = MutableStateFlow(null)
    val orderState: StateFlow<Order?> = _orderState.asStateFlow()

    private val _states: MutableStateFlow<MutableList<StatesVO>> = MutableStateFlow(arrayListOf())
    val states: StateFlow<MutableList<StatesVO>> = _states.asStateFlow()

    private val _stateSelected: MutableStateFlow<StatesVO?> = MutableStateFlow(null)
    val stateSelected: StateFlow<StatesVO?> = _stateSelected.asStateFlow()

    private val order: Order? = savedStateHandle[NavArgs.Order.key]

    init {
        _orderState.value = order
        getStates()
    }

    fun sendOrderState(state: String) {
        viewModelScope.launch(dispatcher.getIODispatcher()) {
            order?.let {
                when (val result = orderStateUseCase.updateState(it, state)) {
                    is ResultState.Error -> {
                        Timber.d("Here - OrderViewModel - Error: ${result.exception}")
                    }

                    is ResultState.Success -> {
                        _orderState.value = result.data
                    }
                }
            }
        }
    }

    fun getStates(): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                when (val result = getStatesUseCase.getStates()) {
                    is ResultState.Error -> {
                        Timber.d("Here - OrderViewModel - Error: ${result.exception}")
                    }

                    is ResultState.Success -> {
                        _states.value = result.data.map { it.toVO() }.toMutableList()
                        updateCheckState(stateName = _orderState.value?.state ?: "")
                    }
                }
            } catch (ex: Exception) {
                Timber.d("Here - OrderViewModel: $ex")
            }
        }
    }

    fun updateCheckState(stateName: String) {
        _states.value.map { stateVO ->
            stateVO.isCheck = false
            if (stateVO.name == stateName) {
                stateVO.isCheck = true
                _stateSelected.value = stateVO
            }
            stateVO
        }
    }

    fun updateOrderStateFromPrint() {
        viewModelScope.launch(dispatcher.getIODispatcher()) {
            if (order?.stateCode == ConstantsDomain.PENDING_ORDER_STATE) {
                when (val result = getStatesUseCase.getStateByCode(ConstantsDomain.PROGRESS_ORDER_STATE)) {
                    is ResultState.Error -> {
                        Timber.d("Here - OrderViewModel - Error: ${result.exception}")
                    }

                    is ResultState.Success -> {
                        sendOrderState(result.data.id)
                    }
                }
            }
        }
    }
}
