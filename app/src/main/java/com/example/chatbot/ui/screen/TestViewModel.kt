package com.example.chatbot.ui.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(TestState())
    val state: StateFlow<TestState> = _state.asStateFlow()

    fun onIntent(intent: TestIntent) {
        when(intent) {
            TestIntent.OnButtonClicked -> {
                _state.update { it.copy(isTextVisible = !it.isTextVisible) }
            }
        }
    }
}