package com.innowise.chatbot.di

import com.innowise.chatbot.data.repository.ChatRepositoryImpl
import com.innowise.chatbot.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindChatRepository(impl: ChatRepositoryImpl): ChatRepository
}
