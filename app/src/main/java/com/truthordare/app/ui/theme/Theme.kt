package com.truthordare.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.truthordare.app.data.ThemeMode
import com.truthordare.app.data.UserSettings

private val DarkColors = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFFB21F4A),
    secondary = androidx.compose.ui.graphics.Color(0xFFFF6B2C),
    tertiary = androidx.compose.ui.graphics.Color(0xFF7A2EFF),
    background = androidx.compose.ui.graphics.Color(0xFF0E0B1A),
    surface = androidx.compose.ui.graphics.Color(0xFF171225),
    onPrimary = androidx.compose.ui.graphics.Color.White,
    onBackground = androidx.compose.ui.graphics.Color.White,
    onSurface = androidx.compose.ui.graphics.Color.White
)

private val LightColors = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFFB21F4A),
    secondary = androidx.compose.ui.graphics.Color(0xFFFF6B2C),
    tertiary = androidx.compose.ui.graphics.Color(0xFF7A2EFF),
    background = androidx.compose.ui.graphics.Color(0xFFF4F1F8),
    surface = androidx.compose.ui.graphics.Color.White,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    onBackground = androidx.compose.ui.graphics.Color(0xFF120F1D),
    onSurface = androidx.compose.ui.graphics.Color(0xFF120F1D)
)

@Composable
fun TruthOrDareTheme(settings: UserSettings, content: @Composable () -> Unit) {
    val isDark = when (settings.themeMode) {
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.AMOLED -> true
    }

    MaterialTheme(
        colorScheme = if (isDark) DarkColors else LightColors,
        content = content
    )
}
