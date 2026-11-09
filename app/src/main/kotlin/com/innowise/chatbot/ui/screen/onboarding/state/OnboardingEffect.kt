package com.innowise.chatbot.ui.screen.onboarding.state

sealed interface OnboardingEffect {
    data object NavigateToChats : OnboardingEffect
}
