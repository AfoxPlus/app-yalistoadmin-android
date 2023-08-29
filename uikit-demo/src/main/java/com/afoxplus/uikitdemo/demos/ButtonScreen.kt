package com.afoxplus.uikitdemo.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.components.ButtonYaListoComponent

@Composable
fun ButtonScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        ButtonYaListoComponent(
            modifier = Modifier.fillMaxWidth(),
            text = "Ingresar",
            enabled = true,
            onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
        ButtonYaListoComponent(
            modifier = Modifier.fillMaxWidth(),
            text = "Ingresar",
            enabled = false,
            onClick = {})
    }
}

@Preview
@Composable
fun ButtonScreenPreview() {
    ButtonScreen()
}
