package com.innowise.chatbot.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innowise.chatbot.ui.screen.auth.state.AuthEffect
import com.innowise.chatbot.ui.screen.auth.state.AuthIntent
import com.innowise.chatbot.ui.screen.auth.state.AuthState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _effect = Channel<AuthEffect>(Channel.BUFFERED)
    val effect: Flow<AuthEffect> = _effect.receiveAsFlow()

    fun onIntent(intent: AuthIntent) {
        when (intent) {
            AuthIntent.OnSignInClicked -> {
                viewModelScope.launch { _effect.send(AuthEffect.NavigateToSignIn) }
            }

            // Google sign-in is not implemented yet, so go straight to the app.
            AuthIntent.OnGoogleClicked -> {
                viewModelScope.launch { _effect.send(AuthEffect.NavigateToChats) }
            }
        }
    }
}
