package com.innowise.chatbot.data.local

import com.innowise.chatbot.domain.model.Chat
import javax.inject.Inject

// TODO: replace with Room database
class ChatLocalDataSource @Inject constructor() {

    suspend fun getChats(): List<Chat> = emptyList()
}
