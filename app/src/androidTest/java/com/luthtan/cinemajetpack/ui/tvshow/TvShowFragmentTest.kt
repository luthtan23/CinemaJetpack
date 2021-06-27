package com.luthtan.cinemajetpack.ui.tvshow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.ui.MainActivity
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TvShowFragmentTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressIdlingResources.idlingResource)
        ActivityScenario.launch(MainActivity::class.java)
        launchFragmentInContainer<TvShowFragment>(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return TvShowFragment()
            }
        }, themeResId = R.style.ThemeCinemaJetpack)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressIdlingResources.idlingResource)
    }

    @Test
    fun loadTvShowPopular() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshow_popular))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshow_popular))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }
}