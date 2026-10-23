package com.example.chatbot.ui.screen

sealed interface TestIntent {
    data object OnButtonClicked: TestIntent
}