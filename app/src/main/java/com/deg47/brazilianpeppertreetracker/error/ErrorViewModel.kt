package com.deg47.brazilianpeppertreetracker.error

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class ErrorViewState {
    object Content : ErrorViewState()
}

class ErrorViewModel : ViewModel() {
    private val _viewState = MutableLiveData<ErrorViewState>()
    val viewState: LiveData<ErrorViewState> = _viewState

    init {
        _viewState.value = ErrorViewState.Content
    }
}