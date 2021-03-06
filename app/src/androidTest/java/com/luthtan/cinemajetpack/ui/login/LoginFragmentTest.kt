package com.luthtan.cinemajetpack.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
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
class LoginFragmentTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressIdlingResources.idlingResource)
        launchFragmentInContainer<LoginFragment>(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return LoginFragment()
            }
        }, themeResId = R.style.ThemeCinemaJetpack)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressIdlingResources.idlingResource)
    }

    @Test
    fun login() {

        onView(withId(R.id.iv_login_logo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.ib_login_setting))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_login_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.outlinedTextField))
            .perform(closeSoftKeyboard())
        onView(withId(R.id.outlinedTextField_password))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.btn_login_login))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.et_username)).perform(typeText("mangbrad23"), closeSoftKeyboard())
        onView(withId(R.id.et_password)).perform(typeText("admin001"), closeSoftKeyboard())
        onView(withId(R.id.btn_login_login)).perform(click())
    }
}