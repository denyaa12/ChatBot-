package com.innowise.chatbot.domain.repository

import com.innowise.chatbot.domain.model.Chat

interface ChatRepository {

    suspend fun getChats(): List<Chat>
}
