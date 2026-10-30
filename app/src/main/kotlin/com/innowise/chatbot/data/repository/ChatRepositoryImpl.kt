package com.innowise.chatbot.data.repository

import com.innowise.chatbot.data.local.ChatLocalDataSource
import com.innowise.chatbot.data.remote.ChatRemoteDataSource
import com.innowise.chatbot.domain.model.Chat
import com.innowise.chatbot.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val localDataSource: ChatLocalDataSource,
    private val remoteDataSource: ChatRemoteDataSource,
) : ChatRepository {

    override suspend fun getChats(): List<Chat> =
        remoteDataSource.getChats().ifEmpty { localDataSource.getChats() }
}
