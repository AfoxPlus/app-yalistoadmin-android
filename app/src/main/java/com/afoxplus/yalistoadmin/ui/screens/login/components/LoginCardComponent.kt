package com.afoxplus.yalistoadmin.ui.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afoxplus.uikitcompose.ui.components.ButtonYaListoComponent
import com.afoxplus.uikitcompose.ui.components.TextFieldYaListoComponent
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Header04Bold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.Paragraph02
import com.afoxplus.yalistoadmin.R

@Composable
fun LoginCardComponent(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    focusManager: FocusManager,
    onLogin: (key: String) -> Unit
) {
    var inputText by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = CardDefaults.cardColors(containerColor = Light01)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.login_card_title),
                    color = Dark01,
                    style = Header04Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.login_card_subtitle),
                    color = Dark05,
                    style = Paragraph01
                )
                Spacer(modifier = Modifier.height(42.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.login_card_input_title),
                    textAlign = TextAlign.Start,
                    color = Dark05,
                    style = Paragraph02
                )
                Spacer(modifier = Modifier.height(2.dp))
                TextFieldYaListoComponent(
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { inputText = it }
                )
                Spacer(modifier = Modifier.height(32.dp))
                ButtonYaListoComponent(
                    text = stringResource(id = R.string.ingresar),
                    enabled = enabled,
                    onClick = {
                        focusManager.clearFocus()
                        onLogin(inputText.text)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginCardComponentPreview() {
    val focusManager = LocalFocusManager.current
    Column {
        LoginCardComponent(focusManager = focusManager, enabled = true, onLogin = {})
        LoginCardComponent(focusManager = focusManager, enabled = false, onLogin = {})
    }
}
