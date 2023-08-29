package com.afoxplus.uikitdemo.demos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
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
    Column(modifier = Modifier.fillMaxSize()) {
        val colors = arrayListOf<ColorVO>(
            ColorVO(
                name = "Dark01",
                backgroundColor = Dark01,
                textColor = Color.White
            ),
            ColorVO(
                name = "Dark02",
                backgroundColor = Dark02,
                textColor = Color.White
            ),
            ColorVO(
                name = "Dark03",
                backgroundColor = Dark03,
                textColor = Color.White
            ),
            ColorVO(
                name = "Dark04",
                backgroundColor = Dark04,
                textColor = Color.White
            ),
            ColorVO(
                name = "Dark05",
                backgroundColor = Dark05,
                textColor = Color.White
            ),
            ColorVO(
                name = "Light01",
                backgroundColor = Light01,
                textColor = Color.Black
            ),
            ColorVO(
                name = "Light02",
                backgroundColor = Light02,
                textColor = Color.Black
            ),
            ColorVO(
                name = "Light03",
                backgroundColor = Light03,
                textColor = Color.Black
            ),
            ColorVO(
                name = "Light04",
                backgroundColor = Light04,
                textColor = Color.Black
            ),
            ColorVO(
                name = "Light05",
                backgroundColor = Light05,
                textColor = Color.Black
            ),
            ColorVO(
                name = "Red01", backgroundColor = Red01, textColor = Color.White
            ),
            ColorVO(
                name = "Green01",
                backgroundColor = Green01,
                textColor = Color.White
            ),
            ColorVO(
                name = "Orange01",
                backgroundColor = Orange01,
                textColor = Color.White
            ),
            ColorVO(
                name = "Pink01",
                backgroundColor = Pink01,
                textColor = Color.White
            ),
            ColorVO(
                name = "Pink02",
                backgroundColor = Pink02,
                textColor = Color.White
            ),
            ColorVO(
                name = "Gray01",
                backgroundColor = Gray01,
                textColor = Color.White
            )
        )
        LazyColumn() {
            items(colors.size) {
                BoxColorComponent(
                    name = colors[it].name,
                    backgroundColor = colors[it].backgroundColor,
                    textColor = colors[it].textColor
                )
            }
        }
    }
}

data class ColorVO(
    val name: String,
    val backgroundColor: Color,
    val textColor: Color
)

@Composable
fun BoxColorComponent(
    name: String,
    backgroundColor: Color,
    textColor: Color
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
            color = textColor,
            style = Paragraph02
        )
    }
}

@Preview
@Composable
fun ColorScreenPreview() {
    ColorScreen()
}
