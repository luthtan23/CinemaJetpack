package com.luthtan.cinemajetpack.ui.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.luthtan.cinemajetpack.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginFragmentTest {

    @Test
    fun login() {

        Espresso.onView(ViewMatchers.withId(R.id.iv_login_logo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.ib_login_setting))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_login_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.outlinedTextField))
            .perform(ViewActions.typeText("mangbrad23"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.outlinedTextField_password))
            .perform(ViewActions.typeText("admin001"), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.btn_login_login))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_login_login)).perform(ViewActions.click())
    }
}