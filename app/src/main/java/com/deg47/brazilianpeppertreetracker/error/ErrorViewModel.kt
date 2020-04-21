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
    object Loading : ErrorViewState()
    data class Content(val navigator: Navigator) : ErrorViewState()
}

class ErrorViewModel(
    private val navigator: Navigator,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableLiveData<ErrorViewState>()
    val viewState: LiveData<ErrorViewState> = _viewState

    init {
        loadErrorContent()
    }

    private fun loadErrorContent() {
        viewModelScope.launch(ioDispatcher) {
            withContext(Dispatchers.Main) {
                _viewState.value = ErrorViewState.Content(navigator)
            }
        }
    }
}