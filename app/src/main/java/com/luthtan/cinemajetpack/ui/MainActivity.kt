package com.luthtan.cinemajetpack.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luthtan.cinemajetpack.databinding.ActivityMainBinding
import com.luthtan.cinemajetpack.listener.BottomNavVisibilityListener

class MainActivity : AppCompatActivity(), BottomNavVisibilityListener {

    private lateinit var bottomView: BottomNavigationView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

    }

    override fun bottomViewComponent(bottomView: BottomNavigationView) {
        this.bottomView = bottomView
    }

    override fun toolbarBottomView(toolbar: MaterialToolbar) {
        this.toolbar = toolbar
    }

    fun showBottomNav() {
        bottomView.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        bottomView.visibility = View.GONE
    }

    fun hideToolbar() {
        toolbar.visibility = View.GONE
    }


}