package xyz.nfcv.pupil.asmd.ui.widget

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Dp
import androidx.ui.core.WithDensity
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.vectorResource

val DEFAULT_WIDTH = 24.dp

@Composable
fun VectorImageButton(@DrawableRes id: Int, width: Dp = DEFAULT_WIDTH, height: Dp = DEFAULT_WIDTH, onClick: () -> Unit) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            VectorImage(id, width = width, height = height)
        }
    }
}

@Composable
fun VectorImage(@DrawableRes id: Int, width: Dp = DEFAULT_WIDTH, height: Dp = DEFAULT_WIDTH, tint: Color = Color.Transparent) {
    val vector = +vectorResource(id)
    WithDensity {
        Container(width = width, height = height) {
            DrawVector(vector, tint)
        }
    }
}

@Composable
fun ImageButton(@DrawableRes id: Int, width: Dp = DEFAULT_WIDTH, height: Dp = DEFAULT_WIDTH, onClick: () -> Unit) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            Image(id, width = width, height = height)
        }
    }
}

@Composable
fun Image(@DrawableRes id: Int, width: Dp = DEFAULT_WIDTH, height: Dp = DEFAULT_WIDTH, tint: Color = Color.Transparent) {
    val image = +vectorResource(id)
    WithDensity {
        Container(width = width, height = height) {
            DrawVector(image, tint)
        }
    }
}
