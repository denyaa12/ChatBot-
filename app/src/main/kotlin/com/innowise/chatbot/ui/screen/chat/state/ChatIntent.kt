package com.innowise.chatbot.ui.screen.chat.state

sealed interface ChatIntent {
    data object OnBackClicked : ChatIntent
}
