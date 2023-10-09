package com.afoxplus.uikitdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afoxplus.uikitcompose.ui.theme.UiKitComposeTheme
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_BOTTOM_SHEET
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_BUTTON
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_CARD_ORDER_TYPE
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_COLOR
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_INPUT
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_MAIN
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_TEXT
import com.afoxplus.uikitdemo.demos.BottomSheetScreen
import com.afoxplus.uikitdemo.demos.ButtonScreen
import com.afoxplus.uikitdemo.demos.CardOrderTypeScreen
import com.afoxplus.uikitdemo.demos.ColorScreen
import com.afoxplus.uikitdemo.demos.InputScreen
import com.afoxplus.uikitdemo.demos.TextScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiKitComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_MAIN
                    ) {
                        composable(route = ROUTE_MAIN) {
                            MainScreen(navController = navController)
                        }
                        composable(route = ROUTE_TEXT) {
                            TextScreen()
                        }
                        composable(route = ROUTE_COLOR) {
                            ColorScreen()
                        }
                        composable(route = ROUTE_BUTTON) {
                            ButtonScreen()
                        }
                        composable(route = ROUTE_INPUT) {
                            InputScreen()
                        }
                        composable(route = ROUTE_CARD_ORDER_TYPE) {
                            CardOrderTypeScreen()
                        }
                        composable(route = ROUTE_BOTTOM_SHEET) {
                            BottomSheetScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(ROUTE_TEXT) }
        ) {
            Text(text = ROUTE_TEXT.uppercase())
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(ROUTE_COLOR) }
        ) {
            Text(text = ROUTE_COLOR.uppercase())
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(ROUTE_BUTTON) }
        ) {
            Text(text = ROUTE_BUTTON.uppercase())
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(ROUTE_INPUT) }
        ) {
            Text(text = ROUTE_INPUT.uppercase())
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(ROUTE_CARD_ORDER_TYPE) }
        ) {
            Text(text = ROUTE_CARD_ORDER_TYPE.uppercase())
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(ROUTE_BOTTOM_SHEET) }
        ) {
            Text(text = ROUTE_BOTTOM_SHEET.uppercase())
        }
    }
}