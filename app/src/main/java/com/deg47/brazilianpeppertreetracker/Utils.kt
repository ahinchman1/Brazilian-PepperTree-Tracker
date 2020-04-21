package com.deg47.brazilianpeppertreetracker

import android.view.View

fun setVisibleOrInvisible(isVisible: Boolean): Int = if (isVisible) View.VISIBLE else View.INVISIBLE

fun setVisibleOrGone(isVisible: Boolean): Int = if (isVisible) View.VISIBLE else View.GONE