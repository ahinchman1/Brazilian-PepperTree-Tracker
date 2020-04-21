package com.deg47.brazilianpeppertreetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

class MainActivity : SingleFragmentActivity() {

    override val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_main

    override fun createFragment(): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
