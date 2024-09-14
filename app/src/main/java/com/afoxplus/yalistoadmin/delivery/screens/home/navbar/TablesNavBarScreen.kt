package com.afoxplus.yalistoadmin.delivery.screens.home.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTypographyTheme

@Composable
fun TablesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = UIKitTheme.spacing.spacing16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UIKitText(
                text = "¡Estamos trabajando para ti!",
                style = UIKitTypographyTheme.header05SemiBold
            )
            UIKitText(
                text = "Muy pronto podrás gestionar tus mesas de manera fácil y eficiente",
                style = UIKitTypographyTheme.paragraph01,
                textAlign = TextAlign.Center
            )
        }
    }
}
