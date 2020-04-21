package com.deg47.brazilianpeppertreetracker.spotter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deg47.brazilianpeppertreetracker.navigator.Navigator

@Suppress("UNCHECKED_CAST")
class TreeSpotterViewModelFactory constructor(
    private val navigator: Navigator
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        SpotterViewModel(navigator) as T
}