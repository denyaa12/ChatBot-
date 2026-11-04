package com.innowise.chatbot.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

// gradient angles from Figma
const val ButtonGradientAngle = 44.37f
const val TextGradientAngle = 61.72f
const val ProgressGradientAngle = 21.91f

fun angledLinearGradient(
    angleDegrees: Float,
    colors: List<Color> = listOf(
        PrimaryCyan,
        PrimaryGreen,
    ),
): Brush = object : ShaderBrush() {

    override fun createShader(size: Size): Shader {
        val radians = Math.toRadians(angleDegrees.toDouble())
        val direction = Offset(
            x = sin(radians).toFloat(),
            y = -cos(radians).toFloat(),
        )
        val halfLength = (abs(size.width * direction.x) + abs(size.height * direction.y)) / 2f
        val center = Offset(
            x = size.width / 2f,
            y = size.height / 2f,
        )

        return LinearGradientShader(
            from = center - direction * halfLength,
            to = center + direction * halfLength,
            colors = colors,
        )
    }
}
