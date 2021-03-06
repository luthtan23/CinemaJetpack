package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.movie.MovieRepositorySource
import com.luthtan.cinemajetpack.vo.Resource

class FakeRepository : MovieRepositorySource {

    override fun getPopularMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        return movieResponse
    }

    override fun getTopRatedMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        return movieResponse
    }

    override fun getUpcomingMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        return movieResponse
    }

    override fun getNowPlayingMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        return movieResponse
    }


}