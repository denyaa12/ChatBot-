package com.innowise.chatbot.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.innowise.chatbot.ui.theme.AppTheme
import com.innowise.chatbot.ui.theme.ButtonGradientAngle
import com.innowise.chatbot.ui.theme.angledLinearGradient

private const val DisabledAlpha = 0.5f

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val dimens = AppTheme.dimens
    val brush = remember { angledLinearGradient(ButtonGradientAngle) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (enabled) 1f else DisabledAlpha)
            .clip(CircleShape)
            .background(brush = brush)
            .clickable(
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(
                horizontal = dimens.dp24,
                vertical = dimens.dp18,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = typography.labelLarge,
            color = colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
private fun GradientButtonPreview() {
    AppTheme {
        GradientButton(
            text = "Button",
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun GradientButtonDisabledPreview() {
    AppTheme {
        GradientButton(
            text = "Button",
            onClick = {},
            enabled = false,
        )
    }
}
