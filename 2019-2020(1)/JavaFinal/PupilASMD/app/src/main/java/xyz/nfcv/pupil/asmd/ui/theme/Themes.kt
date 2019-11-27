package xyz.nfcv.pupil.asmd.ui.theme

import androidx.ui.graphics.Color
import androidx.ui.material.MaterialColors

val lightThemeColors = MaterialColors(
    primary = Color(0xFF26C6DA),
    primaryVariant = Color(0xFF4DD0E1),
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFD00036),
    onError = Color.White
)

/**
 * Note: Dark Theme support is not yet available, it will come in 2020. This is just an example of
 * using dark colors.
 */
val darkThemeColors = MaterialColors(
    primary = Color(0xFF5C6BC0),
    primaryVariant = Color(0xFF7986CB),
    onPrimary = Color.Black,
    secondary = Color(0xFF121212),
    onSecondary = Color.White,
    surface = Color(0xFF121212),
    background = Color(0xFF121212),
    onBackground = Color.White,
    onSurface = Color.White
)
