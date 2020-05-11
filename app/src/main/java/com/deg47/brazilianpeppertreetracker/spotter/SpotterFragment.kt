package com.deg47.brazilianpeppertreetracker.spotter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.deg47.brazilianpeppertreetracker.MainActivity
import com.deg47.brazilianpeppertreetracker.R
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import com.deg47.brazilianpeppertreetracker.navigator.Screen
import com.deg47.brazilianpeppertreetracker.setVisibleOrGone
import kotlinx.android.synthetic.main.fragment_pepper_tree_spotter.*

class SpotterFragment: Fragment() {

    private val navigator by lazy { (requireActivity() as MainActivity).navigator }
    private val viewModel by viewModels<SpotterViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_pepper_tree_spotter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(this, Observer<SpotterViewState> { viewState ->
            viewState?.let { render(it) }
        })
    }

    private fun render(viewState: SpotterViewState) {
        when (viewState) {
            is SpotterViewState.Loading -> {
                pepper_tree_spotter_loader.visibility = setVisibleOrGone(true)
                pepper_tree_spotter_text.visibility = setVisibleOrGone(false)
            }
            is SpotterViewState.Content -> {
                pepper_tree_spotter_loader.visibility = setVisibleOrGone(false)
                pepper_tree_spotter_text.visibility = setVisibleOrGone(true)
            }
            is SpotterViewState.Error -> navigator.navigateToError(from = Screen.SPOTTER)
        }
    }
}