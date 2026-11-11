package com.innowise.chatbot.ui.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innowise.chatbot.ui.screen.signin.state.SignInEffect
import com.innowise.chatbot.ui.screen.signin.state.SignInIntent
import com.innowise.chatbot.ui.screen.signin.state.SignInState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private val EmailRegex = Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")

class SignInViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    private val _effect = Channel<SignInEffect>(Channel.BUFFERED)
    val effect: Flow<SignInEffect> = _effect.receiveAsFlow()

    fun onIntent(intent: SignInIntent) {
        when (intent) {
            SignInIntent.OnBackClicked -> {
                viewModelScope.launch { _effect.send(SignInEffect.NavigateBack) }
            }

            is SignInIntent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = intent.email,
                        isNextEnabled = EmailRegex.matches(intent.email.trim()),
                    )
                }
            }

            SignInIntent.OnNextClicked -> {
                if (_state.value.isNextEnabled) {
                    viewModelScope.launch { _effect.send(SignInEffect.NavigateToChats) }
                }
            }
        }
    }
}
