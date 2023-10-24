package com.afoxplus.yalistoadmin.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.afoxplus.uikit.designsystem.atoms.UIKitLoading
import com.afoxplus.yalistoadmin.ui.screens.login.LoginViewModel

@Composable
fun CheckInScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onSessionActive: () -> Unit,
    onSessionInactive: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        loginViewModel.verifySession(
            onSessionActive = onSessionActive,
            onSessionInactive = onSessionInactive
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UIKitLoading()
    }
}
