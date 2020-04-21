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
    data class Content(val spottedSpottedTrees: List<SpottedTreeModel>) : SpotterViewState()
}

class SpotterViewModel(
    private val navigator: Navigator,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableLiveData<SpotterViewState>()
    val viewState: LiveData<SpotterViewState> = _viewState

    init {
        loadSpotters()
    }

    private fun loadSpotters() {
        viewModelScope.launch(ioDispatcher) {
            withContext(Dispatchers.Main) {
                when (val conference = conferencesRepository.getConference()) {
                    is Result.Success -> getSpeakerModels(conference.data)
                    is Result.Failure -> navigator.navigateToError(from = Screen.SPOTTER)
                }
            }
        }
    }

    private fun getSpeakerModels(conference: SpottedTreeModel) {
        when(val speakers = getSpeakers(conference)) {
            is Result.Success -> _viewState.value =
                SpotterViewState.Content(speakers.data)
            is Result.Failure -> navigator.navigateToError(from = Screen.SPOTTER)
        }
    }

    private fun getSpeakers(): Result<List<SpottedTreeModel>> {
        return when {
            conference.speakers.isNullOrEmpty() -> Timber.d("$TAG: Conference speaker list is empty.")
            else ->  Result.Success(conference.speakers.toSpeakerModels())
        }
    }

    companion object {
        const val TAG = "TREE_SPOTTER_VIEW_MODEL"
    }
}