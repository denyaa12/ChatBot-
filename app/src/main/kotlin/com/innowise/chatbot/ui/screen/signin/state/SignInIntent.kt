package com.innowise.chatbot.ui.screen.signin.state

sealed interface SignInIntent {
    data object OnBackClicked : SignInIntent
    data class OnEmailChanged(val email: String) : SignInIntent
    data object OnNextClicked : SignInIntent
}
