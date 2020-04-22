package com.deg47.brazilianpeppertreetracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deg47.brazilianpeppertreetracker.navigator.Navigator

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(
    private val navigator: Navigator
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        MainActivityViewModel(navigator) as T
}