package com.afoxplus.yalistoadmin.delivery.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.delivery.graphs.NavArgs
import com.afoxplus.yalistoadmin.delivery.models.StatesVO
import com.afoxplus.yalistoadmin.delivery.models.toVO
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.entities.OrderStateCode
import com.afoxplus.yalistoadmin.domain.usecase.ArchiveOrderUseCase
import com.afoxplus.yalistoadmin.domain.usecase.GetStatesUseCase
import com.afoxplus.yalistoadmin.domain.usecase.UpdateOrderStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val orderStateUseCase: UpdateOrderStateUseCase,
    private val getStatesUseCase: GetStatesUseCase,
    private val archiveOrderUseCase: ArchiveOrderUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val mOrderState: MutableStateFlow<OrderStateView> = MutableStateFlow(OrderStateView.Loading)
    val orderState: StateFlow<OrderStateView> = mOrderState.asStateFlow()

    private val _states: MutableStateFlow<MutableList<StatesVO>> = MutableStateFlow(arrayListOf())
    val states: StateFlow<MutableList<StatesVO>> = _states.asStateFlow()

    private val _stateSelected: MutableStateFlow<StatesVO?> = MutableStateFlow(null)
    val stateSelected: StateFlow<StatesVO?> = _stateSelected.asStateFlow()

    private val _orderArchived: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val orderArchived: SharedFlow<Boolean> = _orderArchived

    private val mOrderButtonState: MutableStateFlow<OrderStateButtonView> by lazy { MutableStateFlow(OrderStateButtonView.None) }
    val orderButtonState = mOrderButtonState.asStateFlow()

    private val order: Order? = savedStateHandle[NavArgs.Order.key]

    init {
        order?.let {
            mOrderState.value = OrderStateView.Success(it)
            setupButton(it)
        }
        getStates()
    }

    private fun setupButton(order: Order) {
        when (order.stateCode) {
            OrderStateCode.TODO -> mOrderButtonState.value = OrderStateButtonView.Confirm
            OrderStateCode.PROGRESS -> mOrderButtonState.value = OrderStateButtonView.Finish
            OrderStateCode.DONE,
            OrderStateCode.REJECTED,
            OrderStateCode.DELIVERY -> mOrderButtonState.value = OrderStateButtonView.None
        }
    }

    fun sendOrderState(state: String) {
        viewModelScope.launch(dispatcher.getIODispatcher()) {
            order?.let {
                when (val result = orderStateUseCase.updateState(it, state)) {
                    is ResultState.Error -> {
                        Timber.d("Here - OrderViewModel - Error: ${result.exception}")
                    }

                    is ResultState.Success -> {
                        mOrderState.value = OrderStateView.Success(result.data)
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
                        if (mOrderState.value is OrderStateView.Success) {
                            val state = (mOrderState.value as OrderStateView.Success).data.state
                            updateCheckState(stateName = state)
                        }
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
            if (order?.stateCode == OrderStateCode.TODO) {
                when (
                    val result =
                        getStatesUseCase.getStateByCode(OrderStateCode.PROGRESS.name)
                ) {
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

    fun isUpdateButton(): Boolean {
        return order?.isUpdateButton() ?: false
    }

    fun archiveOrder() {
        viewModelScope.launch(dispatcher.getIODispatcher()) {
            order?.let {
                when (val result = archiveOrderUseCase.archiveOrder(order)) {
                    is ResultState.Error -> {
                        Timber.d("Here - OrderViewModel - Error: ${result.exception}")
                    }

                    is ResultState.Success -> {
                        _orderArchived.emit(true)
                    }
                }
            }
        }
    }

    sealed interface OrderStateView {
        object Loading : OrderStateView
        data class Success(val data: Order) : OrderStateView
    }

    sealed interface OrderStateButtonView {
        object None : OrderStateButtonView
        object Confirm : OrderStateButtonView
        object Finish : OrderStateButtonView
    }
}
