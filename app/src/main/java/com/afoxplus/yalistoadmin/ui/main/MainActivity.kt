package com.afoxplus.yalistoadmin.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.ui.graphs.RootNavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiKitComposeTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
