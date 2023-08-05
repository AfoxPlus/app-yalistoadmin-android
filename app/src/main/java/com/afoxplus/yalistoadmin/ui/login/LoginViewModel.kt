package com.afoxplus.yalistoadmin.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.usecase.AuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: AuthUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _authResultState = MutableStateFlow<ResultState<AuthEntity>>(ResultState.Init())
    val authResultState = _authResultState.asStateFlow()

    private val _isError = mutableStateOf(false)
    var isError = _isError

    private val _isLoading = mutableStateOf(false)
    var isLoading = _isLoading

    fun auth(key: String): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                _authResultState.value = ResultState.Loading()
                val params = AuthParams(key = key)
                val result = useCase.auth(params)
                when (result) {
                    is ResultState.Error -> {
                        _isError.value = true
                        _isLoading.value = false
                    }

                    is ResultState.Init -> {}
                    is ResultState.Loading -> {
                        _isLoading.value = true
                    }

                    is ResultState.Success -> {
                        _isError.value = false
                        _isLoading.value = false
                    }
                }
                Timber.d("Here - TRY-VM: ${result}")
                _authResultState.value = result
            } catch (e: Exception) {
                Timber.d("Here - Catch-VM: ${e.message}")
                _authResultState.value = ResultState.Error(e)
            }
        }
    }
}