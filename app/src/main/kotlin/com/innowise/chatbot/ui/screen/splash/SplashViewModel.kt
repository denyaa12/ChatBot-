package com.innowise.chatbot.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innowise.chatbot.ui.screen.splash.state.SplashEffect
import com.innowise.chatbot.ui.screen.splash.state.SplashState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PROGRESS_STEPS = 10
private const val STEP_DURATION_MILLIS = 200L

class SplashViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        SplashState(
            stepDurationMillis = STEP_DURATION_MILLIS.toInt(),
        ),
    )
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private val _effect = Channel<SplashEffect>(Channel.BUFFERED)
    val effect: Flow<SplashEffect> = _effect.receiveAsFlow()

    init {
        startLoading()
    }

    private fun startLoading() {
        viewModelScope.launch {
            repeat(PROGRESS_STEPS) { step ->
                delay(STEP_DURATION_MILLIS)
                _state.update { it.copy(progress = (step + 1f) / PROGRESS_STEPS) }
            }
            _effect.send(SplashEffect.NavigateToOnboarding)
        }
    }
}
