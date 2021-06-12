package com.luthtan.cinemajetpack.util

import androidx.test.espresso.idling.CountingIdlingResource

object EspressIdlingResources {

    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }

}