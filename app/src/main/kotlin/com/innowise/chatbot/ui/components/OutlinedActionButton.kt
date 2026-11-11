package com.innowise.chatbot.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.innowise.chatbot.R
import com.innowise.chatbot.ui.theme.AppTheme

@Composable
fun OutlinedActionButton(
    text: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .border(
                width = dimens.dp1,
                color = colorScheme.outline,
                shape = CircleShape,
            )
            .clickable(
                role = Role.Button,
                onClick = onClick,
            )
            .padding(
                horizontal = dimens.dp24,
                vertical = dimens.dp18,
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = dimens.dp8,
            alignment = Alignment.CenterHorizontally,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(dimens.dp24),
        )

        Text(
            text = text,
            style = typography.titleMedium,
            color = colorScheme.onBackground,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OutlinedActionButtonPreview() {
    AppTheme {
        OutlinedActionButton(
            text = stringResource(R.string.auth_continue_with_google),
            iconRes = R.drawable.ic_google,
            onClick = {},
        )
    }
}
