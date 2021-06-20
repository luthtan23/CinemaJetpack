package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.movie.MovieRepositoryListener

class FakeRepository : MovieRepositoryListener {

    override suspend fun getPopularMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse> {
        return movieResponse
    }

    override suspend fun getTopRatedMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse> {
        return movieResponse
    }

    override suspend fun getUpcomingMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse> {
        return movieResponse
    }

    override suspend fun getNowPlayingMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse> {
        return movieResponse
    }

}