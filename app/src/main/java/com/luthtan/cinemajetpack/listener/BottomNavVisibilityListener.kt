package com.luthtan.cinemajetpack.listener

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

interface BottomNavVisibilityListener {
    fun bottomViewComponent(bottomView: BottomNavigationView)
    fun toolbarBottomView(toolbar: MaterialToolbar)
}