package com.innowise.chatbot.ui.screen.test.state

sealed interface TestIntent {
    data object OnToggleTextClicked : TestIntent
    data object OnOpenChatsClicked : TestIntent
}
