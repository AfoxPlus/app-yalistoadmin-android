package com.afoxplus.yalistoadmin.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.domain.usecase.GetStatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getStatesUseCase: GetStatesUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _isNavigate = MutableStateFlow(false)
    val isNavigate = _isNavigate.asStateFlow()
    fun states(): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                val result = getStatesUseCase.states()
                _isNavigate.value = true
            } catch (e: Exception) {
                _isNavigate.value = true
            }
        }
    }

}