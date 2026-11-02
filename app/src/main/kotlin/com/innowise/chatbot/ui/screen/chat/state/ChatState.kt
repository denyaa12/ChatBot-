package com.innowise.chatbot.ui.screen.chat.state

import com.innowise.chatbot.domain.model.Chat

data class ChatState(
    val isLoading: Boolean = true,
    val chats: List<Chat> = emptyList(),
)
