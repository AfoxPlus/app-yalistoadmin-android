package com.afoxplus.yalistoadmin.ui.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.usecase.AuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: AuthUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    var isLoading: MutableState<Boolean> = mutableStateOf(false)
    var navigateTo = mutableStateOf(false)
    lateinit var authEntity: AuthEntity

    fun auth(key: String): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            try {
                isLoading.value = true
                val params = AuthParams(key = key)
                val result = useCase.auth(params)
                when (result) {
                    is ResultState.Error -> {
                        isLoading.value = false
                    }

                    is ResultState.Success -> {
                        isLoading.value = false
                        navigateTo.value = true
                        authEntity = result.data
                    }
                }
                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
            }
        }
    }
}