package com.afoxplus.uikitdemo.demos

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikitcompose.ui.components.TextFieldYaListoComponent

@Composable
fun InputScreen() {

    var inputText by remember { mutableStateOf(TextFieldValue("")) }

    TextFieldYaListoComponent(
        modifier = Modifier.fillMaxWidth(),
        placeholder = "Helouda",
        onValueChange = { inputText = it }
    )

}

@Preview
@Composable
fun InputScreenPreview() {
    InputScreen()
}
