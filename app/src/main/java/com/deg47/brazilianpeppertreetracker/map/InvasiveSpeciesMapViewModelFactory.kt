package com.deg47.brazilianpeppertreetracker.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import com.deg47.brazilianpeppertreetracker.spotter.SpotterViewModel

@Suppress("UNCHECKED_CAST")
class InvasiveSpeciesMapViewModelFactory constructor(
    private val navigator: Navigator
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        InvasiveSpeciesMapViewModel(navigator) as T
}