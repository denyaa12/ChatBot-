package com.innowise.chatbot.ui.screen.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.innowise.chatbot.R
import com.innowise.chatbot.ui.components.GradientButton
import com.innowise.chatbot.ui.screen.test.state.TestEffect
import com.innowise.chatbot.ui.screen.test.state.TestIntent
import com.innowise.chatbot.ui.screen.test.state.TestState
import com.innowise.chatbot.ui.theme.AppTheme

@Composable
fun TestRoute(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigateToChats: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TestViewModel = viewModel(factory = viewModelFactory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                TestEffect.NavigateToChats -> onNavigateToChats()
            }
        }
    }

    TestScreen(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier,
    )
}

@Composable
fun TestScreen(
    state: TestState,
    onIntent: (TestIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimens.dp16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = typography.headlineLarge,
            color = colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(dimens.dp24))

        GradientButton(
            text = stringResource(if (state.isTextVisible) R.string.hide else R.string.show),
            onClick = { onIntent(TestIntent.OnToggleTextClicked) },
        )

        Spacer(modifier = Modifier.height(dimens.dp12))

        GradientButton(
            text = stringResource(R.string.open_chats),
            onClick = { onIntent(TestIntent.OnOpenChatsClicked) },
        )

        if (state.isTextVisible) {
            Text(
                text = stringResource(R.string.headline_large),
                style = typography.headlineLarge,
                color = colorScheme.onBackground,
                modifier = Modifier.padding(top = dimens.dp24),
            )
            Text(
                text = stringResource(R.string.headline_medium),
                style = typography.headlineMedium,
                color = colorScheme.onBackground,
                modifier = Modifier.padding(top = dimens.dp24),
            )
            Text(
                text = stringResource(R.string.headline_small),
                style = typography.headlineSmall,
                color = colorScheme.onBackground,
                modifier = Modifier.padding(top = dimens.dp24),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TestScreenPreview() {
    AppTheme {
        TestScreen(
            state = TestState(isTextVisible = true),
            onIntent = {},
        )
    }
}
