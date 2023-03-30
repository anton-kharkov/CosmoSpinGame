package net.gelbelninoteam.cosmospingame.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = SoftPink,
    primaryVariant = BrightYellow,
    secondary = DeepBluePurple,
)

@Composable
fun CosmoSpinGameTheme(
    content: @Composable () -> Unit
) {
    val colors = DarkColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}