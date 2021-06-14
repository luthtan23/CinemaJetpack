package com.luthtan.cinemajetpack.ui.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import org.json.JSONException
import org.junit.Test
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class MovieFragmentTest {

    @Test
    fun loadMoviePopular() {
        var json: String? = null
        try {
            val inputStream: InputStream = InstrumentationRegistry.getInstrumentation().context.assets.open(
                "movie_popular.json"
            )
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        var movieResponse: MovieResponse? = null
        try {
            val jsonString = json
            if (jsonString != null) {
                movieResponse = Gson().fromJson(jsonString, MovieResponse::class.java)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie_popular))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie_popular)).perform(movieResponse?.results?.size?.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                it
            )
        })
    }
}