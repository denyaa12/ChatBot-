package com.innowise.chatbot.ui.screen.splash.state

sealed interface SplashEffect {
    data object NavigateToOnboarding : SplashEffect
}
