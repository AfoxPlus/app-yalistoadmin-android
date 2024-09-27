package com.afoxplus.yalistoadmin.delivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.usecase.GetAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase,
    private val dispatcher: UIKitCoroutineDispatcher
) : ViewModel() {

    private val _auth: MutableStateFlow<AuthStateView> = MutableStateFlow(AuthStateView.Loading)
    val auth: StateFlow<AuthStateView> = _auth.asStateFlow()

    fun getAuth(): Job {
        return viewModelScope.launch(dispatcher.getIODispatcher()) {
            _auth.value = AuthStateView.Success(data = getAuthUseCase.authPreferences())
        }
    }

    sealed interface AuthStateView {
        object Loading : AuthStateView
        data class Success(val data: Auth) : AuthStateView
    }
}
