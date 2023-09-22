package com.afoxplus.yalistoadmin.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikitcompose.ui.theme.Header03Bold

@Composable
fun OrderStatusScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Orden Screen", style = Header03Bold)
    }
}

@Preview
@Composable
fun OrderStatusScreenPreview() {
    OrderStatusScreen()
}
