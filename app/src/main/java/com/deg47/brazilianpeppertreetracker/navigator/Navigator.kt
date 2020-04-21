package com.deg47.brazilianpeppertreetracker.navigator

import android.content.res.Resources
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.deg47.brazilianpeppertreetracker.R

class Navigator(
    private val navController: NavController,
    private val resources: Resources
) {

    fun navigateToError(from: Screen) {
        val currentDestination = navController.currentDestination?.id
        if (currentDestination != R.id.errorFragment) {
            val bundle = bundleOf(
                PREVIOUS_DESTINATION_ID to from.fragmentId(),
                PREVIOUS_DESTINATION_LABEL to from.fragmentLabel()

            )
            navController.navigate(R.id.action_go_to_error, bundle)
        }
    }

    fun navigateToInvasiveSpeciesMap() {
        if (navController.currentDestination?.id != Screen.INVASIVE_SPECIES_MAP.fragmentId()) {
            navController.navigate(R.id.action_go_to_invasive_species_map)
        }
    }

    fun navigateToTreeSpotter() {
        if (navController.currentDestination?.id != Screen.SPOTTER.fragmentId()) {
            navController.navigate(R.id.action_go_to_tree_spotter)
        }
    }

    private fun Screen.fragmentId(): Int {
        return when (this) {
            Screen.INVASIVE_SPECIES_MAP -> R.id.invasiveSpeciesMapFragment
            Screen.SPOTTER -> R.id.treeSpotterFragment
            else -> R.id.errorFragment
        }
    }

    private fun Screen.fragmentLabel(): String {
        return when (this) {
            Screen.INVASIVE_SPECIES_MAP -> resources.getString(R.string.invasive_species_map_label)
            Screen.SPOTTER -> resources.getString(R.string.tree_spotter_label)
            else -> resources.getString(R.string.generalMessageError)
        }
    }

    companion object {
        const val PREVIOUS_DESTINATION_ID = "PREVIOUS_DESTINATION_ID"
        const val PREVIOUS_DESTINATION_LABEL = "PREVIOUS_DESTINATION_LABEL"
    }
}