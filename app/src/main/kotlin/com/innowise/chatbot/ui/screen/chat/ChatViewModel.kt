package com.innowise.chatbot.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innowise.chatbot.domain.usecase.GetChatsUseCase
import com.innowise.chatbot.ui.screen.chat.state.ChatEffect
import com.innowise.chatbot.ui.screen.chat.state.ChatIntent
import com.innowise.chatbot.ui.screen.chat.state.ChatState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ChatState())
    val state: StateFlow<ChatState> = _state.asStateFlow()

    private val _effect = Channel<ChatEffect>(Channel.BUFFERED)
    val effect: Flow<ChatEffect> = _effect.receiveAsFlow()

    init {
        loadChats()
    }

    fun onIntent(intent: ChatIntent) {
        when (intent) {
            ChatIntent.OnBackClicked -> {
                viewModelScope.launch { _effect.send(ChatEffect.NavigateBack) }
            }
        }
    }

    private fun loadChats() {
        viewModelScope.launch {
            val chats = getChatsUseCase()
            _state.update { it.copy(isLoading = false, chats = chats) }
        }
    }
}
