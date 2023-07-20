package com.afoxplus.yalistoadmin.ui.theme

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
import com.afoxplus.uikitcompose.ui.theme.Dark01
import com.afoxplus.uikitcompose.ui.theme.Green01
import com.afoxplus.uikitcompose.ui.theme.Pink02
import com.afoxplus.uikitcompose.ui.theme.TypographyUiKit

private val DarkColorScheme = darkColorScheme(
    primary = Dark01,
    secondary = Green01,
    tertiary = Pink02
)

private val LightColorScheme = lightColorScheme(
    primary = Dark01,
    secondary = Green01,
    tertiary = Pink02
)

@Composable
fun AppyalistoadminandroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TypographyUiKit,
        //shapes = ShapesUiKit,
        content = content
    )
}
