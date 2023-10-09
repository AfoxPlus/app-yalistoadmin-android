package com.afoxplus.uikitdemo.demos

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikitcompose.ui.components.BottomSheetComponent

@Composable
fun BottomSheetScreen() {
    BottomSheetComponent(
        title = "Helouda",
        atributo = "info",
        //list = arrayListOf("Heloda", "Como", "Estas"),
        //list = arrayListOf(Person("Carlos", 28), Person("Neli", 29)),
        list= arrayListOf(Info(1,"Informacion 1", description = "info 1"),Info(2,"Informacion 2", description = "info 2"),Info(3,"Informacion 3", description = "info 3")),
        onClick = {
            Log.d("TAG", "Here - BottomSheetScreen: $it")
        },
        onDismiss = {
            Log.d("TAG", "Here - Dismiss BottomSheetScreen: ")
        })
}

@Preview
@Composable
fun BottomSheetScreenPreview() {
    BottomSheetScreen()
}

data class Person(
    val name: String,
    val age: Int
)

data class Info(
    val id: Int,
    val info: String,
    val description: String,
    val list: List<String> = emptyList()
)