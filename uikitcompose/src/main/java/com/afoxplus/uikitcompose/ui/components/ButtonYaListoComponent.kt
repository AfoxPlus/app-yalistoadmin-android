package com.afoxplus.uikitcompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Orange01

@Composable
fun ButtonYaListoComponent(
    text: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Orange01),
    shape: Shape = RoundedCornerShape(15.dp),
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            onClick()
        },
        colors = colors,
        shape = shape
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.CenterVertically),
            text = text,
            textAlign = TextAlign.Center,
            style = Header05SemiBold,
            color = Light01
        )
    }
}

@Preview
@Composable
fun ButtonYaListoComponentPreview() {
    ButtonYaListoComponent(text = "Ingresar", modifier = Modifier.fillMaxWidth(), onClick = {})
}