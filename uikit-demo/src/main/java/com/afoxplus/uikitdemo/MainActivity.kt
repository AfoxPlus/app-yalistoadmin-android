package com.afoxplus.uikitdemo

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_COLOR
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_MAIN
import com.afoxplus.uikitdemo.ScreenNames.ROUTE_TEXT
import com.afoxplus.uikitdemo.demos.ColorScreen
import com.afoxplus.uikitdemo.demos.TextScreen
import com.afoxplus.uikitdemo.ui.theme.AppyalistoadminandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppyalistoadminandroidTheme {
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
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
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
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppyalistoadminandroidTheme {}
}