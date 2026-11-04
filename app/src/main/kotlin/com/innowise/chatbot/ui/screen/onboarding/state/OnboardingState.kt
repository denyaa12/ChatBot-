package com.innowise.chatbot.ui.screen.onboarding.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.innowise.chatbot.R

data class OnboardingState(
    @StringRes val headlineRes: Int = R.string.onboarding_headline,
    @StringRes val headlineHighlightsRes: List<Int> = listOf(
        R.string.onboarding_headline_highlight_first,
        R.string.onboarding_headline_highlight_second,
    ),
    @StringRes val descriptionRes: Int = R.string.onboarding_description,
    @DrawableRes val imageRes: Int = R.drawable.img_onboarding_robot,
    @StringRes val buttonTextRes: Int = R.string.onboarding_get_started,
)
