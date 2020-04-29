package com.deg47.brazilianpeppertreetracker.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deg47.brazilianpeppertreetracker.Result
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import com.deg47.brazilianpeppertreetracker.navigator.Screen
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

sealed class InvasiveSpeciesMapViewState {
    object Loading : InvasiveSpeciesMapViewState()
    data class Content(val invasiveSpecies: InvasiveSpeciesLocations) : InvasiveSpeciesMapViewState()
}

class InvasiveSpeciesMapViewModel(
    private val navigator: Navigator,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableLiveData<InvasiveSpeciesMapViewState>()
    val viewState: LiveData<InvasiveSpeciesMapViewState> = _viewState

    init {
        _viewState.value = InvasiveSpeciesMapViewState.Loading
        onMapReady()
    }

    private fun onMapReady() {
        viewModelScope.launch(ioDispatcher) {
            withContext(Dispatchers.Main) {
                Timber.d("$TAG Failed to retrieve Google maps.")
                navigator.navigateToError(from = Screen.INVASIVE_SPECIES_MAP)
            }
        }
    }

    companion object {
        const val TAG = "INVASIVE_SPECIES_MAP_VIEW_MODEL"
    }
}