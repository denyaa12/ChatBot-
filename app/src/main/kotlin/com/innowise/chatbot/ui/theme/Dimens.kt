package com.innowise.chatbot.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val dp1: Dp = 1.dp,
    val dp4: Dp = 4.dp,
    val dp8: Dp = 8.dp,
    val dp12: Dp = 12.dp,
    val dp15: Dp = 15.dp,
    val dp16: Dp = 16.dp,
    val dp18: Dp = 18.dp,
    val dp20: Dp = 20.dp,
    val dp24: Dp = 24.dp,
    val dp32: Dp = 32.dp,
    val dp36: Dp = 36.dp,
    val dp40: Dp = 40.dp,
    val dp52: Dp = 52.dp,
    val dp56: Dp = 56.dp,
    val dp81: Dp = 81.dp,
    val dp92: Dp = 92.dp,
    val dp128: Dp = 128.dp,
    val dp178: Dp = 178.dp,
)

val LocalDimens = staticCompositionLocalOf { Dimens() }
