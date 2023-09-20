package com.afoxplus.yalistoadmin.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.usecase.GetStatesUseCase
import com.afoxplus.yalistoadmin.domain.usecase.SaveStatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getStatesUseCase: GetStatesUseCase,
    private val saveStatesUseCase: SaveStatesUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _isNavigate = MutableStateFlow(false)
    val isNavigate = _isNavigate.asStateFlow()

    fun states(): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                when (val result = getStatesUseCase.getStates()) {
                    is ResultState.Error -> {
                        Timber.d("Here - SplashViewModel - Error: ${result.exception.message}")
                        Timber.d("Here - SplashViewModel - Error: ${result.exception}")
                    }

                    is ResultState.Success -> {
                        Timber.d("Here - SplashViewModel - Success: ${result.data}")
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
