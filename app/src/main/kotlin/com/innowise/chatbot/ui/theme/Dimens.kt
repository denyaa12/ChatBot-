package com.innowise.chatbot.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val dp4: Dp = 4.dp,
    val dp8: Dp = 8.dp,
    val dp12: Dp = 12.dp,
    val dp16: Dp = 16.dp,
    val dp20: Dp = 20.dp,
    val dp24: Dp = 24.dp,
    val dp32: Dp = 32.dp,
)

val LocalDimens = staticCompositionLocalOf { Dimens() }
