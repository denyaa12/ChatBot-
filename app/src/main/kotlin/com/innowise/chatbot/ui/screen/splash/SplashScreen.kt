package com.innowise.chatbot.ui.screen.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.innowise.chatbot.R
import com.innowise.chatbot.ui.components.BackgroundBubbles
import com.innowise.chatbot.ui.components.GradientProgressBar
import com.innowise.chatbot.ui.screen.splash.state.SplashEffect
import com.innowise.chatbot.ui.screen.splash.state.SplashState
import com.innowise.chatbot.ui.theme.AppTheme

@Composable
fun SplashRoute(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigateToOnboarding: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = viewModel(factory = viewModelFactory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SplashEffect.NavigateToOnboarding -> onNavigateToOnboarding()
            }
        }
    }

    SplashScreen(
        state = state,
        modifier = modifier,
    )
}

@Composable
fun SplashScreen(
    state: SplashState,
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens

    val progress by animateFloatAsState(
        targetValue = state.progress,
        animationSpec = tween(
            durationMillis = SplashViewModel.STEP_DURATION_MILLIS.toInt(),
            easing = LinearEasing,
        ),
        label = "splashProgress",
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BackgroundBubbles(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = dimens.dp16),
        )

        Spacer(modifier = Modifier.height(dimens.dp40))

        Image(
            painter = painterResource(R.drawable.ic_logo_vertical),
            contentDescription = stringResource(R.string.logo_content_description),
            modifier = Modifier.size(dimens.dp92),
        )

        Spacer(modifier = Modifier.height(dimens.dp20))

        GradientProgressBar(progress = progress)

        Spacer(modifier = Modifier.height(dimens.dp40))

        BackgroundBubbles(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = dimens.dp16),
            mirrored = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    AppTheme {
        SplashScreen(state = SplashState(progress = 0.34f))
    }
}
