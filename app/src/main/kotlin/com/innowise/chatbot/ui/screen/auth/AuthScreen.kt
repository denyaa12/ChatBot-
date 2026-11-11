package com.innowise.chatbot.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.innowise.chatbot.R
import com.innowise.chatbot.ui.components.BackgroundBubbles
import com.innowise.chatbot.ui.components.GradientButton
import com.innowise.chatbot.ui.components.OutlinedActionButton
import com.innowise.chatbot.ui.screen.auth.state.AuthEffect
import com.innowise.chatbot.ui.screen.auth.state.AuthIntent
import com.innowise.chatbot.ui.screen.auth.state.AuthState
import com.innowise.chatbot.ui.theme.AppTheme

@Composable
fun AuthRoute(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigateToSignIn: () -> Unit,
    onNavigateToChats: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = viewModel(factory = viewModelFactory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                AuthEffect.NavigateToSignIn -> onNavigateToSignIn()
                AuthEffect.NavigateToChats -> onNavigateToChats()
            }
        }
    }

    AuthScreen(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier,
    )
}

@Composable
fun AuthScreen(
    state: AuthState,
    onIntent: (AuthIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens

    Box(modifier = modifier.fillMaxSize()) {
        BackgroundBubbles(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = dimens.dp40),
            mirrored = true,
        )

        BackgroundBubbles(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(bottom = dimens.dp128),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimens.dp16),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo_vertical),
                contentDescription = stringResource(R.string.logo_content_description),
                modifier = Modifier.size(dimens.dp128),
            )

            Spacer(modifier = Modifier.height(dimens.dp20))

            Text(
                text = stringResource(state.titleRes),
                style = typography.headlineLarge,
                color = colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(dimens.dp8))

            Text(
                text = stringResource(state.subtitleRes),
                style = typography.bodyLarge,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(dimens.dp92))

            GradientButton(
                text = stringResource(state.signInButtonRes),
                onClick = { onIntent(AuthIntent.OnSignInClicked) },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(dimens.dp12))

            OutlinedActionButton(
                text = stringResource(state.googleButtonRes),
                iconRes = state.googleIconRes,
                onClick = { onIntent(AuthIntent.OnGoogleClicked) },
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 402,
    heightDp = 822,
)
@Composable
private fun AuthScreenPreview() {
    AppTheme {
        AuthScreen(
            state = AuthState(),
            onIntent = {},
        )
    }
}
