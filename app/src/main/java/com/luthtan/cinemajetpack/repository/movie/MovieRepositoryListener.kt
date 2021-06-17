package com.luthtan.cinemajetpack.repository.movie

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse

interface MovieRepositoryListener {

    suspend fun getPopularMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse>

    suspend fun getTopRatedMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse>

    suspend fun getUpcomingMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse>

    suspend fun getNowPlayingMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse>

}