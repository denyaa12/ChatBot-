package com.innowise.chatbot.ui.components

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import com.innowise.chatbot.ui.theme.AppTheme
import com.innowise.chatbot.ui.theme.PrimaryCyan
import com.innowise.chatbot.ui.theme.PrimaryGreen

private const val FrameWidth = 128f
private const val BubblesAlpha = 0.2f
private const val SigmaToBlurRadius = 1.73f

private data class Bubble(
    val center: Offset,
    val radius: Float,
    val gradientCenter: Offset,
    val gradientRadius: Float,
    val blurSigma: Float = 0f,
)

private val Bubbles = listOf(
    Bubble(
        center = Offset(113.34f, 99.24f),
        radius = 65.44f,
        gradientCenter = Offset(89.94f, 70.9f),
        gradientRadius = 129f,
    ),
    Bubble(
        center = Offset(23.86f, 23.73f),
        radius = 23.8f,
        gradientCenter = Offset(15.35f, 13.43f),
        gradientRadius = 47f,
        blurSigma = 2f,
    ),
    Bubble(
        center = Offset(28.33f, 166.13f),
        radius = 11.9f,
        gradientCenter = Offset(24.08f, 160.98f),
        gradientRadius = 23.5f,
        blurSigma = 4f,
    ),
)

@Composable
fun BackgroundBubbles(
    modifier: Modifier = Modifier,
    mirrored: Boolean = false,
) {
    val dimens = AppTheme.dimens

    Canvas(
        modifier = modifier
            .size(
                width = dimens.dp128,
                height = dimens.dp178,
            ),
    ) {
        val unit = size.width / FrameWidth

        scale(
            scaleX = if (mirrored) -1f else 1f,
            scaleY = 1f,
        ) {
            Bubbles.forEach { bubble -> drawBubble(bubble, unit) }
        }
    }
}

private fun DrawScope.drawBubble(
    bubble: Bubble,
    unit: Float,
) {
    val paint = Paint().apply {
        isAntiAlias = true
        alpha = BubblesAlpha
        shader = RadialGradientShader(
            center = bubble.gradientCenter * unit,
            radius = bubble.gradientRadius * unit,
            colors = listOf(
                PrimaryGreen,
                PrimaryCyan,
            ),
        )
        if (bubble.blurSigma > 0f) {
            asFrameworkPaint().maskFilter = BlurMaskFilter(
                bubble.blurSigma * SigmaToBlurRadius * unit,
                BlurMaskFilter.Blur.NORMAL,
            )
        }
    }

    drawIntoCanvas { canvas ->
        canvas.drawCircle(
            center = bubble.center * unit,
            radius = bubble.radius * unit,
            paint = paint,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BackgroundBubblesPreview() {
    AppTheme {
        BackgroundBubbles()
    }
}

@Preview(showBackground = true)
@Composable
private fun BackgroundBubblesMirroredPreview() {
    AppTheme {
        BackgroundBubbles(mirrored = true)
    }
}
