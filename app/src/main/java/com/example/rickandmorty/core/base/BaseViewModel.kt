package com.example.rickandmorty.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<ViewState, Event, Effect>(
    initialState: ViewState
): ViewModel() {

    private val _viewState = MutableStateFlow(initialState)
    val viewState get() = _viewState.asStateFlow()

    private val _effects = Channel<Effect>()
    val effects get() = _effects.receiveAsFlow()

    abstract fun onEvent(event: Event)

    protected fun updateState(transform: ViewState.() -> ViewState) {
        _viewState.update { oldState -> transform.invoke(oldState) }
    }
    protected suspend fun sendEffect(effect: Effect) = _effects.send(effect)

    protected fun <T> Flow<T>.stateInViewModel(initialValue: T) = stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = initialValue
    )

}