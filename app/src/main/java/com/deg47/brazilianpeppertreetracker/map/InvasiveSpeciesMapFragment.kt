package com.deg47.brazilianpeppertreetracker.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.deg47.brazilianpeppertreetracker.R
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import com.deg47.brazilianpeppertreetracker.setVisibleOrGone
import kotlinx.android.synthetic.main.fragment_invasive_species_map.*

class InvasiveSpeciesMapFragment: Fragment() {

    private val navController by lazy {
        Navigation.findNavController(requireActivity(), R.id.fragment_container)
    }

    private val viewModel by viewModels<InvasiveSpeciesMapViewModel> {
        InvasiveSpeciesMapViewModelFactory(navigator = Navigator(navController, resources))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewState.observe(this, Observer<InvasiveSpeciesMapViewState> { viewState ->
            viewState?.let { render(it) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_invasive_species_map, container, false)

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
        }
    }
}