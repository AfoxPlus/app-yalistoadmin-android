package com.afoxplus.yalistoadmin.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.yalistoadmin.commons.utils.Screen
import com.afoxplus.yalistoadmin.ui.home.HomeActivity
import com.afoxplus.yalistoadmin.ui.login.LoginScreen
import com.afoxplus.yalistoadmin.ui.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiKitComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(onFinish = {
                        finish()
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    })
                }
            }
        }
    }
}

@Composable
fun Navigation(onFinish: () -> Unit) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navigateTo = {
                navController.popBackStack()
                navController.navigate("login_screen") {

                }
            })
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navigateTo = {
                onFinish()
            })
        }
    }
}