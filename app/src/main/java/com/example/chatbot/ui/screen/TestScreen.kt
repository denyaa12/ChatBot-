package com.example.chatbot.ui.screen

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatbot.R
import com.example.chatbot.ui.theme.Dimens
import com.example.chatbot.ui.theme.PrimaryGradient

@Composable
fun TestScreen(
    state: TestState,
    onIntent: (TestIntent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.dp16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = typography.headlineLarge,
            color = colorScheme.onBackground
        )

        Spacer(Modifier.padding(Dimens.dp24))

        Box(
            modifier = Modifier
                .background(brush = PrimaryGradient, shape = RoundedCornerShape(Dimens.dp12))
                .clickable { onIntent(TestIntent.OnButtonClicked) }
                .padding(horizontal = Dimens.dp32, vertical = Dimens.dp16)
        ) {
            Text(
                text = if (state.isTextVisible) stringResource(R.string.hide) else stringResource(R.string.show),
                color = colorScheme.onPrimary,
            )
        }
        if (state.isTextVisible) {
            Text(
                text = stringResource(R.string.headline_large),
                style = typography.headlineLarge,
                color = colorScheme.onBackground,
                modifier = Modifier.padding(top = Dimens.dp24)
            )
            Text(
                text = stringResource(R.string.headline_small),
                style = typography.headlineSmall,
                color = colorScheme.onBackground,
                modifier = Modifier.padding(top = Dimens.dp24)
            )
            Text(
                text = stringResource(R.string.headline_medium),
                style = typography.headlineMedium,
                color = colorScheme.onBackground,
                modifier = Modifier.padding(top = Dimens.dp24)
            )
        }
    }
}

@Preview
@Composable
private fun TestScreenPreview() {
    TestScreen(
        state = TestState(),
        onIntent = {}
    )
}

