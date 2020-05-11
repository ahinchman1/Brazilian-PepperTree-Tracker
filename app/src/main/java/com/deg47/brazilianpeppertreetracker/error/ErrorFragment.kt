package com.deg47.brazilianpeppertreetracker.error

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
import com.deg47.brazilianpeppertreetracker.navigator.Navigator.Companion.PREVIOUS_DESTINATION_ID
import com.deg47.brazilianpeppertreetracker.navigator.Navigator.Companion.PREVIOUS_DESTINATION_LABEL
import com.deg47.brazilianpeppertreetracker.navigator.Screen
import kotlinx.android.synthetic.main.fragment_error.*

class ErrorFragment: Fragment() {

    private val navigator by lazy { (requireActivity() as MainActivity).navigator }
    private val viewModel by viewModels<ErrorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_error, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.title = requireArguments().getString(PREVIOUS_DESTINATION_LABEL, "")

        viewModel.viewState.observe(this, Observer<ErrorViewState> { viewState ->
            viewState?.let { render(it) }
        })
    }

    private fun render(viewState: ErrorViewState) {
        when (viewState) {
            is ErrorViewState.Content -> {
                error_button.setOnClickListener {
                    activity?.title?.let { _ ->
                        when (requireArguments().getInt(PREVIOUS_DESTINATION_ID, 0)) {
                            R.id.invasiveSpeciesMapFragment -> navigator.navigateToInvasiveSpeciesMap()
                            R.id.treeSpotterFragment -> navigator.navigateToTreeSpotter()
                            else -> navigator.navigateToError(from = Screen.ERROR)
                        }
                    }
                }
            }
        }
    }
}