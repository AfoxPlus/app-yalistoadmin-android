package com.afoxplus.yalistoadmin.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.usecase.GetAuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()

    private var _isNavigate = MutableStateFlow(false)
    var isNavigate = _isNavigate.asStateFlow()

    lateinit var authEntity: AuthEntity

    fun auth(key: String): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                _isLoading.value = true
                val params = AuthParams(key = key)
                val result = getAuthUseCase.auth(params)
                when (result) {
                    is ResultState.Error -> {
                        _isLoading.value = false
                    }

                    is ResultState.Success -> {
                        _isLoading.value = false
                        _isNavigate.value = true
                        authEntity = result.data
                    }
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }
}