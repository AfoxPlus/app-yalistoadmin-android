package com.afoxplus.yalistoadmin.delivery.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.delivery.components.home.BottomBarYaListo
import com.afoxplus.yalistoadmin.delivery.components.home.TopBarYaListo
import com.afoxplus.yalistoadmin.delivery.graphs.HomeNavGraph
import com.afoxplus.yalistoadmin.delivery.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    appState: YaListoHomeState = rememberYaListoHomeState(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val authState by viewModel.auth.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = appState.shouldShowBar
            ) {
                when (authState) {
                    HomeViewModel.AuthStateView.Loading -> {}
                    is HomeViewModel.AuthStateView.Success -> {
                        TopBarYaListo((authState as HomeViewModel.AuthStateView.Success).data)
                    }
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = appState.shouldShowBar) {
                BottomBarYaListo(
                    appState = appState,
                    onNavigateToDestination = appState::navigate
                )
            }
        },
        contentWindowInsets = WindowInsets(
            left = 0.dp,
            top = 0.dp,
            right = 0.dp,
            bottom = 0.dp
        )
    ) { innerPadding ->
        HomeNavGraph(
            navController = appState.navController,
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .consumeWindowInsets(paddingValues = innerPadding)
        )
    }
    LaunchedEffect(key1 = Unit) { viewModel.getAuth() }
}
