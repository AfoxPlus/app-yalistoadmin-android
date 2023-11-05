package com.afoxplus.yalistoadmin.ui.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
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
            tint = UIKitTheme.colors.primaryColor
        )
    }
}

@Preview
@Composable
fun LoginLogoComponentPreviewPreview() {
    LoginLogoComponent()
}
