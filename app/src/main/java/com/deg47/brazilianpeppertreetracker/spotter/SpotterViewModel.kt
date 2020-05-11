package com.deg47.brazilianpeppertreetracker.spotter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import com.deg47.brazilianpeppertreetracker.navigator.Screen
import com.deg47.brazilianpeppertreetracker.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

sealed class SpotterViewState {
    object Loading : SpotterViewState()
    data class Content(val spottedSpottedTrees: List<TreeSpotterModel>) : SpotterViewState()
    object Error: SpotterViewState()
}

class SpotterViewModel : ViewModel() {

    private val _viewState = MutableLiveData<SpotterViewState>()
    val viewState: LiveData<SpotterViewState> = _viewState

    init {
        loadSpotters()
    }

    private fun loadSpotters() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val treeSpotters = getTreeSpotters()) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _viewState.value = SpotterViewState.Content(treeSpotters.data)
                }
                is Result.Failure -> withContext(Dispatchers.Main) {
                    Timber.d("$TAG Error: Unable to load tree spotters.")
                    _viewState.value = SpotterViewState.Error
                }
            }
        }
    }

    private fun getTreeSpotters(): Result<List<TreeSpotterModel>> {
        return Result.Success(listOf())
    }

    companion object {
        const val TAG = "TREE_SPOTTER_VIEW_MODEL"
    }
}