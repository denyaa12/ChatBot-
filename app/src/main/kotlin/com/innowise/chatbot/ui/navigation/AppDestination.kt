package com.innowise.chatbot.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {

    @Serializable
    data object Test : AppDestination

    @Serializable
    data object Chats : AppDestination
}
