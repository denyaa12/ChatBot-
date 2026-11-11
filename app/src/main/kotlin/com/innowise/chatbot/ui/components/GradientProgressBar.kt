package com.innowise.chatbot.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.innowise.chatbot.ui.theme.AppTheme
import com.innowise.chatbot.ui.theme.Gray200
import com.innowise.chatbot.ui.theme.ProgressGradientAngle
import com.innowise.chatbot.ui.theme.angledLinearGradient

@Composable
fun GradientProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens
    val brush = remember { angledLinearGradient(ProgressGradientAngle) }
    val clampedProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .size(
                width = dimens.dp178,
                height = dimens.dp4,
            )
            .clip(CircleShape)
            .background(Gray200)
            .semantics {
                progressBarRangeInfo = ProgressBarRangeInfo(
                    current = clampedProgress,
                    range = 0f..1f,
                )
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(clampedProgress)
                .clip(CircleShape)
                .background(brush),
        )
    }
}

@Preview
@Composable
private fun GradientProgressBarPreview() {
    AppTheme {
        GradientProgressBar(progress = 0.34f)
    }
}
