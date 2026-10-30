package com.innowise.chatbot.ui.screen.chat.state

sealed interface ChatEffect {
    data object NavigateBack : ChatEffect
}
