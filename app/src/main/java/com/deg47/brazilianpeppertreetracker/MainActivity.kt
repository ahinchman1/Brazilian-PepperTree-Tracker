package com.deg47.brazilianpeppertreetracker

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.deg47.brazilianpeppertreetracker.navigator.Navigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : SingleFragmentActivity() {

    override val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_main

    private val navController by lazy { findNavController(R.id.fragment_container) }

    private val appBarConfigurations by lazy {
        AppBarConfiguration
            .Builder(topLevelDestinations)
            .setDrawerLayout(drawerLayout)
            .build()
    }
    private val viewModel by viewModels<MainActivityViewModel> {
        MainActivityViewModelFactory(navigator = Navigator(navController, resources))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigation()

        viewModel.viewState.observe(this, Observer<MainActivityViewState> { viewState ->
            viewState?.let { render(it) }
        })
    }

    private fun render(viewState: MainActivityViewState) {
        when (viewState) {
            is MainActivityViewState.Loading -> {
                loader.visibility = setVisibleOrGone(true)
                fragment_container.visibility = setVisibleOrGone(false)
            }
            is MainActivityViewState.Content -> {
                loader.visibility = setVisibleOrGone(false)
                fragment_container.visibility = setVisibleOrGone(true)
            }
        }
    }

    /**
     * Navigation Component setup -
     * includes top-level destinations so the hamburger menu can be accessed even on Navigation up
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfigurations)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {
        // Update action bar to reflect navigation
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfigurations)

        // Handle nav drawer item clicks
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        // Tie nav graph to items in nav drawer
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    companion object {
        val topLevelDestinations = hashSetOf(
            R.id.errorFragment,
            R.id.invasiveSpeciesMapFragment,
            R.id.treeSpotterFragment
        )
    }
}
