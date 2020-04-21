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

class InvasiveSpeciesMapFragment: Fragment() {

    private val navController by lazy {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
    }

    private val viewModel by viewModels<InvasiveSpeciesMapViewModel> {
        InvasiveSpeciesMapViewModelFactory(
            navigator = Navigator(navController),

        )
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
            is InvasiveSpeciesMapViewState.Loading -> { }
            is InvasiveSpeciesMapViewState.Content -> {

            }
        }
    }

}