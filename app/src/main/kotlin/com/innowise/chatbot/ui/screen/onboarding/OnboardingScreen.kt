package com.innowise.chatbot.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.innowise.chatbot.ui.components.GradientButton
import com.innowise.chatbot.ui.components.LogoBadge
import com.innowise.chatbot.ui.screen.onboarding.state.OnboardingEffect
import com.innowise.chatbot.ui.screen.onboarding.state.OnboardingIntent
import com.innowise.chatbot.ui.screen.onboarding.state.OnboardingState
import com.innowise.chatbot.ui.theme.AppTheme
import com.innowise.chatbot.ui.theme.TextGradientAngle
import com.innowise.chatbot.ui.theme.angledLinearGradient

private val ImageAlignment = BiasAlignment(
    horizontalBias = 0.32f,
    verticalBias = 0f,
)

@Composable
fun OnboardingRoute(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigateToChats: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = viewModel(factory = viewModelFactory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                OnboardingEffect.NavigateToChats -> onNavigateToChats()
            }
        }
    }

    OnboardingScreen(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier,
    )
}

@Composable
fun OnboardingScreen(
    state: OnboardingState,
    onIntent: (OnboardingIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens

    val headline = stringResource(state.headlineRes)
    val highlights = state.headlineHighlightsRes.map { stringResource(it) }
    val textBrush = remember { angledLinearGradient(TextGradientAngle) }
    val annotatedHeadline = remember(headline, highlights) {
        headline.withGradientHighlights(
            highlights = highlights,
            brush = textBrush,
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimens.dp16),
    ) {
        Spacer(modifier = Modifier.height(dimens.dp16))

        LogoBadge()

        Spacer(modifier = Modifier.height(dimens.dp32))

        Text(
            text = annotatedHeadline,
            style = typography.headlineLarge,
            color = colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(dimens.dp20))

        Text(
            text = stringResource(state.descriptionRes),
            style = typography.bodyLarge,
            color = colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(dimens.dp32))

        Image(
            painter = painterResource(state.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = ImageAlignment,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(dimens.dp32))
                .background(colorScheme.surfaceVariant),
        )

        Spacer(modifier = Modifier.height(dimens.dp32))

        GradientButton(
            text = stringResource(state.buttonTextRes),
            onClick = { onIntent(OnboardingIntent.OnGetStartedClicked) },
        )

        Spacer(modifier = Modifier.height(dimens.dp8))
    }
}

private fun String.withGradientHighlights(
    highlights: List<String>,
    brush: Brush,
): AnnotatedString = buildAnnotatedString {
    append(this@withGradientHighlights)

    highlights
        .filter { it.isNotEmpty() }
        .forEach { highlight ->
            var start = indexOf(highlight)
            while (start >= 0) {
                addStyle(
                    style = SpanStyle(brush = brush),
                    start = start,
                    end = start + highlight.length,
                )
                start = indexOf(highlight, startIndex = start + highlight.length)
            }
        }
}

@Preview(
    showBackground = true,
    widthDp = 402,
    heightDp = 822,
)
@Composable
private fun OnboardingScreenPreview() {
    AppTheme {
        OnboardingScreen(
            state = OnboardingState(),
            onIntent = {},
        )
    }
}
