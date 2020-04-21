package com.deg47.brazilianpeppertreetracker.spotter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.deg47.brazilianpeppertreetracker.R
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import com.deg47.brazilianpeppertreetracker.setVisibleOrGone
import kotlinx.android.synthetic.main.fragment_pepper_tree_spotter.*

class TreeSpotterFragment: Fragment() {

    private val navController by lazy {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
    }

    private val viewModel by viewModels<SpotterViewModel> {
        TreeSpotterViewModelFactory(
            navigator = Navigator(navController)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewState.observe(this, Observer<SpotterViewState> { viewState ->
            viewState?.let { render(it) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_pepper_tree_spotter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pepper_tree_spotter_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = speakerAdapter
        }
    }

    private fun render(viewState: SpotterViewState) {
        when (viewState) {
            is SpotterViewState.Loading -> {
                pepper_tree_spotter_loader.visibility = setVisibleOrGone(true)
                pepper_tree_spotter_recycler_view.visibility = setVisibleOrGone(false)
            }
            is SpotterViewState.Content -> {
                updateSpeakers(viewState.speakers)
                pepper_tree_spotter_loader.visibility = setVisibleOrGone(false)
                pepper_tree_spotter_recycler_view.visibility = setVisibleOrGone(true)
            }
        }
    }

    private fun updateSpeakers(speakers: List<SpeakerModel>) {
        speakerAdapter?.updateSpeakers(speakers)
        speakerAdapter?.notifyDataSetChanged()
    }
}