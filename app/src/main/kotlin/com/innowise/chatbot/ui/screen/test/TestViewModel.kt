package com.innowise.chatbot.ui.screen.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innowise.chatbot.ui.screen.test.state.TestEffect
import com.innowise.chatbot.ui.screen.test.state.TestIntent
import com.innowise.chatbot.ui.screen.test.state.TestState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(TestState())
    val state: StateFlow<TestState> = _state.asStateFlow()

    private val _effect = Channel<TestEffect>(Channel.BUFFERED)
    val effect: Flow<TestEffect> = _effect.receiveAsFlow()

    fun onIntent(intent: TestIntent) {
        when (intent) {
            TestIntent.OnToggleTextClicked -> {
                _state.update { it.copy(isTextVisible = !it.isTextVisible) }
            }

            TestIntent.OnOpenChatsClicked -> {
                viewModelScope.launch { _effect.send(TestEffect.NavigateToChats) }
            }
        }
    }
}
