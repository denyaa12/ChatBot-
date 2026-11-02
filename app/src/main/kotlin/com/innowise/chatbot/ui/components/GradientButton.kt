package com.innowise.chatbot.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.innowise.chatbot.ui.theme.AppTheme
import com.innowise.chatbot.ui.theme.PrimaryGradient

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens
    val shape = RoundedCornerShape(dimens.dp12)

    Box(
        modifier = modifier
            .clip(shape)
            .background(brush = PrimaryGradient, shape = shape)
            .clickable(onClick = onClick)
            .padding(horizontal = dimens.dp32, vertical = dimens.dp16),
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
