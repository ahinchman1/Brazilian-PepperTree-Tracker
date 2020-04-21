package com.deg47.brazilianpeppertreetracker.error

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import com.deg47.brazilianpeppertreetracker.spotter.SpotterViewModel

@Suppress("UNCHECKED_CAST")
class ErrorViewModelFactory constructor(
    private val navigator: Navigator
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        SpotterViewModel(navigator) as T
}