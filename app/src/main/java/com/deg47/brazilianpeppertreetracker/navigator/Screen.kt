package com.deg47.brazilianpeppertreetracker.navigator

import android.content.res.Resources

val resources: Resources = Resources.getSystem()

enum class Screen(var screen: String) {
    ERROR("Error"),
    INVASIVE_SPECIES_MAP("Invasive Species Map"),
    SPOTTER("Spotter");

    override fun toString(): String = screen
}
