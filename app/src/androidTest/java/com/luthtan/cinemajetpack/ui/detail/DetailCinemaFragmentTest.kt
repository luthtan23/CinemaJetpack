package com.luthtan.cinemajetpack.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
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
class DetailCinemaFragmentTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressIdlingResources.idlingResource)

        launchFragmentInContainer<DetailCinemaFragment>(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return DetailCinemaFragment()
            }
        }, themeResId = R.style.ThemeCinemaJetpack)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressIdlingResources.idlingResource)
    }

    @Test
    fun loadDetail() {

        onView(withId(R.id.tv_detail_content_title)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tv_detail_content_tagline)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tv_detail_content_released_date)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tv_detail_content_duration)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tv_detail_content_genre)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.detail_content_image)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.progress_bar_detail_content_user_score)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tv_detail_content_overview)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.rv_detail_content_staring)).check(matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.rv_detail_content_recommendation)).check(matches(ViewMatchers.isDisplayed()))

    }
}