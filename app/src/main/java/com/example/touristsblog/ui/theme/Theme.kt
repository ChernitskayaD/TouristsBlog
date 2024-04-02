package com.example.touristsblog.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val colorScheme = Colors(
    primary = BrandPrimary,
    primaryVariant = BrandPrimary,
    secondary = BrandPrimary,
    secondaryVariant = BrandPrimary,
    background = Background,
    surface = BrandPrimary,
    error = BrandPrimary,
    onPrimary = TextOnPrimary,
    onSecondary = TextOnSecondary,
    onBackground = TextOnSecondary,
    onSurface = TextOnSecondary,
    onError = BrandPrimary,
    isLight = false
)

@Composable
fun TouristsBlogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}