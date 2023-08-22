package com.afoxplus.yalistoadmin.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.uikitcompose.ui.theme.Light06
import com.afoxplus.yalistoadmin.commons.extensions.noRippleClickable
import com.afoxplus.yalistoadmin.ui.login.components.LoginCardComponent
import com.afoxplus.yalistoadmin.ui.login.components.LoginLogoComponent

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateTo: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val isLoading by loginViewModel.isLoading.collectAsStateWithLifecycle()
    val isNavigate by loginViewModel.isNavigate.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = isNavigate) {
        if (isNavigate) {
            navigateTo()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Light06)
            .noRippleClickable { focusManager.clearFocus() }
    ) {
        LoginLogoComponent(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        LoginCardComponent(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
            enabled = !isLoading,
            focusManager = focusManager,
            onLogin = { key ->
                loginViewModel.auth(key = key)
            }
        )
    }
}
