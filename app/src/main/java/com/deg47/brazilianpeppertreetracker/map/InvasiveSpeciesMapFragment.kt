package com.deg47.brazilianpeppertreetracker.map

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
import kotlinx.android.synthetic.main.fragment_invasive_species_map.*

class InvasiveSpeciesMapFragment: Fragment() {

    private val navigator by lazy { (requireActivity() as MainActivity).navigator }
    private val viewModel by viewModels<InvasiveSpeciesMapViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_invasive_species_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observeForever { viewState ->
            viewState?.let { render(it) }
        }
    }

    private fun render(viewState: InvasiveSpeciesMapViewState) {
        when (viewState) {
            is InvasiveSpeciesMapViewState.Loading -> {
                invasive_species_map_loader.visibility = setVisibleOrGone(true)
                map.visibility = setVisibleOrGone(false)
            }
            is InvasiveSpeciesMapViewState.Content -> {
                invasive_species_map_loader.visibility = setVisibleOrGone(false)
                map.visibility = setVisibleOrGone(true)
            }
            is InvasiveSpeciesMapViewState.Error ->
                navigator.navigateToError(from = Screen.INVASIVE_SPECIES_MAP)
        }
    }
}