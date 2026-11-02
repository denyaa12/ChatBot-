package com.innowise.chatbot.ui.screen.test.state

sealed interface TestEffect {
    data object NavigateToChats : TestEffect
}
