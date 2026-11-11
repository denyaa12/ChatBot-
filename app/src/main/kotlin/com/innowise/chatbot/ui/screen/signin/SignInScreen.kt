package com.innowise.chatbot.ui.screen.signin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.innowise.chatbot.R
import com.innowise.chatbot.ui.components.AppTextField
import com.innowise.chatbot.ui.components.GradientButton
import com.innowise.chatbot.ui.screen.signin.state.SignInEffect
import com.innowise.chatbot.ui.screen.signin.state.SignInIntent
import com.innowise.chatbot.ui.screen.signin.state.SignInState
import com.innowise.chatbot.ui.theme.AppTheme

@Composable
fun SignInRoute(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigateBack: () -> Unit,
    onNavigateToChats: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel(factory = viewModelFactory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SignInEffect.NavigateBack -> onNavigateBack()
                SignInEffect.NavigateToChats -> onNavigateToChats()
            }
        }
    }

    SignInScreen(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier,
    )
}

@Composable
fun SignInScreen(
    state: SignInState,
    onIntent: (SignInIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = AppTheme.dimens

    val terms = stringResource(state.termsRes)
    val highlights = listOf(
        stringResource(state.termsHighlightFirstRes),
        stringResource(state.termsHighlightSecondRes),
    )
    val highlightColor = colorScheme.primary
    val annotatedTerms = remember(terms, highlights, highlightColor) {
        terms.withHighlights(
            highlights = highlights,
            style = SpanStyle(
                color = highlightColor,
                fontWeight = FontWeight.SemiBold,
            ),
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimens.dp56),
            contentAlignment = Alignment.Center,
        ) {
            IconButton(
                onClick = { onIntent(SignInIntent.OnBackClicked) },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = dimens.dp8),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.back),
                    tint = colorScheme.onBackground,
                )
            }

            Text(
                text = stringResource(state.toolbarTitleRes),
                style = typography.titleMedium,
                color = colorScheme.onBackground,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimens.dp16),
        ) {
            Spacer(modifier = Modifier.height(dimens.dp32))

            Text(
                text = stringResource(state.headlineRes),
                style = typography.headlineMedium,
                color = colorScheme.onBackground,
            )

            Spacer(modifier = Modifier.height(dimens.dp8))

            Text(
                text = stringResource(state.descriptionRes),
                style = typography.bodyMedium,
                color = colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(dimens.dp40))

            AppTextField(
                value = state.email,
                onValueChange = { onIntent(SignInIntent.OnEmailChanged(it)) },
                placeholder = stringResource(state.emailPlaceholderRes),
                leadingIconRes = state.emailIconRes,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { onIntent(SignInIntent.OnNextClicked) },
                ),
            )

            Spacer(modifier = Modifier.height(dimens.dp32))

            GradientButton(
                text = stringResource(state.nextButtonRes),
                onClick = { onIntent(SignInIntent.OnNextClicked) },
                enabled = state.isNextEnabled,
            )

            Spacer(modifier = Modifier.height(dimens.dp24))

            Text(
                text = annotatedTerms,
                style = typography.bodyMedium,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

private fun String.withHighlights(
    highlights: List<String>,
    style: SpanStyle,
): AnnotatedString = buildAnnotatedString {
    append(this@withHighlights)

    highlights
        .filter { it.isNotEmpty() }
        .forEach { highlight ->
            var start = indexOf(highlight)
            while (start >= 0) {
                addStyle(
                    style = style,
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
private fun SignInScreenPreview() {
    AppTheme {
        SignInScreen(
            state = SignInState(
                email = "sample@email.com",
                isNextEnabled = true,
            ),
            onIntent = {},
        )
    }
}
