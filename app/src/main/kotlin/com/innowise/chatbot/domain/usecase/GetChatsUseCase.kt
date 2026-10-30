package com.innowise.chatbot.domain.usecase

import com.innowise.chatbot.domain.model.Chat
import com.innowise.chatbot.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {

    suspend operator fun invoke(): List<Chat> = chatRepository.getChats()
}
