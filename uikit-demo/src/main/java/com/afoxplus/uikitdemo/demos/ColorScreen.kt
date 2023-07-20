package com.afoxplus.uikitdemo.demos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Dark02
import com.afoxplus.uikitcompose.ui.theme.Dark03
import com.afoxplus.uikitcompose.ui.theme.Dark04
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Gray01
import com.afoxplus.uikitcompose.ui.theme.Green01
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light02
import com.afoxplus.uikitcompose.ui.theme.Light03
import com.afoxplus.uikitcompose.ui.theme.Light04
import com.afoxplus.uikitcompose.ui.theme.Light05
import com.afoxplus.uikitcompose.ui.theme.Orange01
import com.afoxplus.uikitcompose.ui.theme.Paragraph02
import com.afoxplus.uikitcompose.ui.theme.Pink01
import com.afoxplus.uikitcompose.ui.theme.Pink02
import com.afoxplus.uikitcompose.ui.theme.Red01

@Composable
fun ColorScreen() {
    Column {
        BoxColorComponent(name = "Dark01", backgroundColor = Dark01, colorText = Color.White)
        BoxColorComponent(name = "Dark02", backgroundColor = Dark02, colorText = Color.White)
        BoxColorComponent(name = "Dark03", backgroundColor = Dark03, colorText = Color.White)
        BoxColorComponent(name = "Dark04", backgroundColor = Dark04, colorText = Color.White)
        BoxColorComponent(name = "Dark05", backgroundColor = Dark05, colorText = Color.White)

        BoxColorComponent(name = "Light01", backgroundColor = Light01, colorText = Color.Black)
        BoxColorComponent(name = "Light02", backgroundColor = Light02, colorText = Color.Black)
        BoxColorComponent(name = "Light03", backgroundColor = Light03, colorText = Color.Black)
        BoxColorComponent(name = "Light04", backgroundColor = Light04, colorText = Color.Black)
        BoxColorComponent(name = "Light05", backgroundColor = Light05, colorText = Color.Black)

        BoxColorComponent(name = "Red01", backgroundColor = Red01, colorText = Color.White)
        BoxColorComponent(name = "Green01", backgroundColor = Green01, colorText = Color.White)
        BoxColorComponent(name = "Orange01", backgroundColor = Orange01, colorText = Color.White)
        BoxColorComponent(name = "Pink01", backgroundColor = Pink01, colorText = Color.White)
        BoxColorComponent(name = "Pink02", backgroundColor = Pink02, colorText = Color.White)
        BoxColorComponent(name = "Gray01", backgroundColor = Gray01, colorText = Color.White)
    }
}

@Composable
fun BoxColorComponent(
    name: String,
    backgroundColor: Color,
    colorText: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            color = colorText,
            style = Paragraph02
        )
    }
}

@Preview
@Composable
fun ColorScreenPreview() {
    ColorScreen()
}
