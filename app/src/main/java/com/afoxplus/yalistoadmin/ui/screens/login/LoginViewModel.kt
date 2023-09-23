package com.afoxplus.yalistoadmin.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.usecase.GetAuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.SaveAuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase,
    private val saveAuthUseCase: SaveAuthUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var _isNavigate: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isNavigate: StateFlow<Boolean> = _isNavigate.asStateFlow()

    fun auth(key: String): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                _isLoading.value = true
                val params = AuthParams(key = key)
                when (val result = getAuthUseCase.auth(params)) {
                    is ResultState.Error -> {
                        _isLoading.value = false
                    }

                    is ResultState.Success -> {
                        _isLoading.value = false
                        _isNavigate.value = true
                        saveAuthUseCase.saveAuth(auth = result.data)
                    }
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun verifySession(onSessionActive: () -> Unit, onSessionInactive: () -> Unit) =
        viewModelScope.launch(dispatcher.getMainDispatcher()) {
            if (isSessionActive()) onSessionActive() else onSessionInactive()
        }

    private suspend fun isSessionActive(): Boolean {
        return getAuthUseCase.authPreferences().code.isNotEmpty()
    }
}
