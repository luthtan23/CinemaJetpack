package com.luthtan.cinemajetpack.ui.favorite.tablayout.tvshow_favorite

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.Util
import com.luthtan.cinemajetpack.ui.MainActivity
import com.luthtan.cinemajetpack.ui.detail.DetailCinemaFragment
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFavoriteFragmentTest {

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
    fun loadTvShowFavorite() {

        onView(withId(R.id.tv_detail_content_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_content_tagline))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_content_released_date))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_content_duration))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_content_genre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.detail_content_image))
            .check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar_detail_content_user_score))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_content_overview))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_detail_content_staring))
            .check(matches(isDisplayed()))

        onView(withId(R.id.ib_detail_content_favorite))
            .perform(ViewActions.click())

        onView(withId(R.id.rv_detail_content_recommendation))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_detail_content_recommendation))
            .perform(ViewActions.scrollTo())
            .check(matches(isDisplayed()))

        onView(withId(R.id.btn_detail_content_trailer)).perform(ViewActions.scrollTo())
            .check(matches(isDisplayed()))

        onView(withId(R.id.btn_detail_content_trailer))
            .check(matches(isDisplayed()))

        launchFragmentInContainer<TvShowFavoriteFragment>(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return TvShowFavoriteFragment()
            }
        }, themeResId = R.style.ThemeCinemaJetpack)

        try {
            //view movie favorite that has clicked favorites

            onView(withId(R.id.rv_favorite_tv_show_layout))
                .check(matches(isDisplayed()))

            //set unfavorite in movie favorite item
            onView(withId(R.id.rv_favorite_movie_layout))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        Util.clickChildViewWithId(R.id.ib_item_favorite_favorite_button)
                    )
                )
        } catch (e: Exception) {
            Log.d("Attention", "Set your tv show favorite first")
            onView(withId(R.id.iv_tv_show_favorite_not_found))
                .check(matches(isDisplayed()))
        }

    }
}