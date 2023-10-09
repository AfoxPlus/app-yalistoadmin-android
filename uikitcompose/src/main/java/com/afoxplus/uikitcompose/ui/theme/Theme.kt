package com.afoxplus.uikitcompose.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorSchemeUiKit = darkColorScheme(
    primary = Dark01,
    secondary = Dark02,
    tertiary = Dark03,
    background = Light01
)

val LightColorSchemeUiKit = lightColorScheme(
    primary = Dark01,
    secondary = Dark02,
    tertiary = Dark03,
    background = Light01
)

@Composable
fun UiKitComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorSchemeUiKit
        else -> LightColorSchemeUiKit
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TypographyUiKit,
        content = content
    )
}
