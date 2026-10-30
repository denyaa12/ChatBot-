package com.innowise.chatbot.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// main colors from Figma
val PrimaryCyan = Color(0xFF09B9D1)
val PrimaryGreen = Color(0xFF40F287)

val Gray900 = Color(0xFF101828)
val Gray700 = Color(0xFF344054)
val Gray500 = Color(0xFF667085)
val Gray300 = Color(0xFFD0D5DD)
val Gray100 = Color(0xFFF2F4F7)
val Gray50 = Color(0xFFF9FAFB)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

// gradient for our buttons
val PrimaryGradient = Brush.horizontalGradient(
    colors = listOf(
        PrimaryCyan,
        PrimaryGreen,
    ),
)

// for dark theme (in future)
val DarkBackground = Color(0xFF0C111D)
val DarkSurface = Color(0xFF1A2232)
