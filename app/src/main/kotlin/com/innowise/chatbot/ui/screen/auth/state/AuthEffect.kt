package com.innowise.chatbot.ui.screen.auth.state

sealed interface AuthEffect {
    data object NavigateToSignIn : AuthEffect
    data object NavigateToChats : AuthEffect
}
