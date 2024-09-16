package com.afoxplus.yalistoadmin.delivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.usecase.GetStatesUseCase
import com.afoxplus.yalistoadmin.domain.usecase.SaveStatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getStatesUseCase: GetStatesUseCase,
    private val saveStatesUseCase: SaveStatesUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _isNavigate = MutableStateFlow(false)
    val isNavigate = _isNavigate.asStateFlow()

    init {
        states()
    }

    fun states(): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                when (val result = getStatesUseCase.getStates()) {
                    is ResultState.Error -> {}
                    is ResultState.Success -> {
                        saveStatesUseCase.saveStates(result.data)
                    }
                }
                _isNavigate.value = true
            } catch (e: Exception) {
                _isNavigate.value = true
            }
        }
    }
}
