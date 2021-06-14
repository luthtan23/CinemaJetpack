package com.luthtan.cinemajetpack.ui.splash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.luthtan.cinemajetpack.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SplashScreenFragmentTest {

    @Test
    fun testSplashImageIsDisplayed() {
        onView(withId(R.id.splashImageView)).check(matches(isDisplayed()))
    }
}