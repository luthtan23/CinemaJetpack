package com.luthtan.cinemajetpack.util

import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.ui.MainActivity
import org.json.JSONException
import java.io.IOException
import java.nio.charset.StandardCharsets


object DummyDataJson {

    private fun loadDataMoviePopularJson(activity: MainActivity): String? {
        val json: String?
        try {
            val inputStream = activity.assets.open("movie_popular.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }

    fun getDummyMoviePopularData(activity: MainActivity): MovieResponse? {
        var movieResponse: MovieResponse? = null
        try {
            val jsonString = loadDataMoviePopularJson(activity)
            if (jsonString != null) {
                movieResponse = Gson().fromJson(jsonString, MovieResponse::class.java)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
        return movieResponse
    }
}