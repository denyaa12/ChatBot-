package com.innowise.chatbot.ui.screen.onboarding.state

sealed interface OnboardingIntent {
    data object OnGetStartedClicked : OnboardingIntent
}
