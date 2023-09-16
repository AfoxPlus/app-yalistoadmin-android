package com.afoxplus.yalistoadmin.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afoxplus.yalistoadmin.commons.utils.NavArgs
import com.afoxplus.yalistoadmin.domain.entities.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val order: Order? = savedStateHandle[NavArgs.Order.key]
    var orderState by mutableStateOf<Order?>(null)
        private set

    init {
        orderState = order
    }
}
