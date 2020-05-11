package com.deg47.brazilianpeppertreetracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class MainActivityViewState {
    object Loading : MainActivityViewState()
    object Content : MainActivityViewState()
}

class MainActivityViewModel : ViewModel() {

    private val _viewState = MutableLiveData<MainActivityViewState>()
    val viewState: LiveData<MainActivityViewState> = _viewState

    init {
        _viewState.value = MainActivityViewState.Loading
        loadContent()
    }

    private fun loadContent() {
        _viewState.value = MainActivityViewState.Content
    }
}