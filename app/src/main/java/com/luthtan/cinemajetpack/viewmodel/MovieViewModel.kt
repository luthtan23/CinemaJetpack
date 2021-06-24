package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.movie.MovieRepository
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel(), KoinComponent {

    private val _moviePopularResponse = MutableLiveData<Resource<MovieResponse>>()
    val moviePopularResponse: LiveData<Resource<MovieResponse>> get() = _moviePopularResponse

    private val _movieTopRatedResponse = MutableLiveData<Resource<MovieResponse>>()
    val movieTopRatedResponse: LiveData<Resource<MovieResponse>> get() = _movieTopRatedResponse

    private val _movieNowPlayingResponse = MutableLiveData<Resource<MovieResponse>>()
    val movieNowPlayingResponse: LiveData<Resource<MovieResponse>> get() = _movieNowPlayingResponse

    private val _movieUpcomingResponse = MutableLiveData<Resource<MovieResponse>>()
    val movieUpcomingResponse: LiveData<Resource<MovieResponse>> get() = _movieUpcomingResponse

    fun getMovieResponse() {
        movieRepository.getPopularMovie(_moviePopularResponse)
        movieRepository.getNowPlayingMovie(_movieNowPlayingResponse)
        movieRepository.getTopRatedMovie(_movieTopRatedResponse)
        movieRepository.getUpcomingMovie(_movieUpcomingResponse)
    }

}