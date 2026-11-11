package com.innowise.chatbot.ui.screen.auth.state

sealed interface AuthIntent {
    data object OnSignInClicked : AuthIntent
    data object OnGoogleClicked : AuthIntent
}
