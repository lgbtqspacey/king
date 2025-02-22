package com.lgbtqspacey.king.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

val lightScheme = Colors(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryVariant = primaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryVariant = secondaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    isLight = true
)

val darkScheme = Colors(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryVariant = primaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryVariant = secondaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    isLight = false,
)

@Composable
fun AppTheme(isDarkMode: Boolean, content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = if (isDarkMode) darkScheme else lightScheme,
        typography = getTypography(),
        content = content
    )
}

