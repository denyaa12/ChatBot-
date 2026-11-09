package com.innowise.chatbot.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.innowise.chatbot.R
import com.innowise.chatbot.ui.theme.AppTheme
import com.innowise.chatbot.ui.theme.Gray900

@Composable
fun LogoBadge(
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens

    Box(
        modifier = modifier
            .height(dimens.dp36)
            .background(
                color = Gray900,
                shape = CircleShape,
            )
            .padding(
                start = dimens.dp12,
                end = dimens.dp16,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo_horizontal),
            contentDescription = stringResource(R.string.logo_content_description),
            modifier = Modifier.size(
                width = dimens.dp81,
                height = dimens.dp15,
            ),
        )
    }
}

@Preview
@Composable
private fun LogoBadgePreview() {
    AppTheme {
        LogoBadge()
    }
}
