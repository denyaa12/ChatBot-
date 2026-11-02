package com.innowise.chatbot.ui.screen.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.innowise.chatbot.ui.screen.chat.state.ChatEffect
import com.innowise.chatbot.ui.screen.chat.state.ChatIntent
import com.innowise.chatbot.ui.screen.chat.state.ChatState
import com.innowise.chatbot.ui.theme.AppTheme

@Composable
fun ChatRoute(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel(factory = viewModelFactory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ChatEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    ChatScreen(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier,
    )
}

@Composable
fun ChatScreen(
    state: ChatState,
    onIntent: (ChatIntent) -> Unit,
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
            text = stringResource(R.string.chats_title),
            style = typography.headlineMedium,
            color = colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(dimens.dp24))

        when {
            state.isLoading -> CircularProgressIndicator()

            state.chats.isEmpty() -> Text(
                text = stringResource(R.string.chats_empty),
                style = typography.bodyLarge,
                color = colorScheme.onSurfaceVariant,
            )

            else -> state.chats.forEach { chat ->
                Text(
                    text = chat.title,
                    style = typography.titleMedium,
                    color = colorScheme.onBackground,
                    modifier = Modifier.padding(vertical = dimens.dp8),
                )
            }
        }

        Spacer(modifier = Modifier.height(dimens.dp24))

        GradientButton(
            text = stringResource(R.string.back),
            onClick = { onIntent(ChatIntent.OnBackClicked) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatScreenPreview() {
    AppTheme {
        ChatScreen(
            state = ChatState(isLoading = false),
            onIntent = {},
        )
    }
}
