package com.innowise.chatbot.data.remote

import com.innowise.chatbot.domain.model.Chat
import javax.inject.Inject

// TODO: replace with AI chat API client
class ChatRemoteDataSource @Inject constructor() {

    suspend fun getChats(): List<Chat> = emptyList()
}
