package com.innowise.chatbot.ui.screen.signin.state

sealed interface SignInEffect {
    data object NavigateBack : SignInEffect
    data object NavigateToChats : SignInEffect
}
