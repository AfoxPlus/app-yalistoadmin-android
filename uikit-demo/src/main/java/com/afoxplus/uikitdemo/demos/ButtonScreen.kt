package com.afoxplus.uikitdemo.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikitcompose.ui.components.ButtonYaListoComponent

@Composable
fun ButtonScreen() {
    Column {
        ButtonYaListoComponent(
            modifier = Modifier.fillMaxWidth(),
            text = "Ingresar",
            enabled = true,
            onClick = {})

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
