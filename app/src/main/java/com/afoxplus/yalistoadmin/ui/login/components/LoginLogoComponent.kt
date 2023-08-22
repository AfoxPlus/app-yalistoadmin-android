package com.afoxplus.yalistoadmin.ui.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikitcompose.ui.theme.Orange01
import com.afoxplus.yalistoadmin.R

@Composable
fun LoginLogoComponent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.yalisto_logo),
            contentDescription = "yalisto_logo",
            tint = Orange01
        )
    }
}

@Preview
@Composable
fun LoginLogoComponentPreviewPreview() {
    LoginLogoComponent()
}
