package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.MovieRepository
import org.koin.core.KoinComponent

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel(), KoinComponent {

    private val _moviePopularResponse = MutableLiveData<MovieResponse>()
    val moviePopularResponse: LiveData<MovieResponse> get() = _moviePopularResponse

    private val _movieTopRatedResponse = MutableLiveData<MovieResponse>()
    val movieTopRatedResponse: LiveData<MovieResponse> get() = _movieTopRatedResponse

    private val _movieNowPlayingResponse = MutableLiveData<MovieResponse>()
    val movieNowPlayingResponse: LiveData<MovieResponse> get() = _movieNowPlayingResponse

    private val _movieUpcomingResponse = MutableLiveData<MovieResponse>()
    val movieUpcomingResponse: LiveData<MovieResponse> get() = _movieUpcomingResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    fun getMovieResponse() {
        movieRepository.getPopularMovie(_moviePopularResponse, _errorResponse)
        movieRepository.getNowPlayingMovie(_movieNowPlayingResponse, _errorResponse)
        movieRepository.getTopRatedMovie(_movieTopRatedResponse, _errorResponse)
        movieRepository.getUpcomingMovie(_movieUpcomingResponse, _errorResponse)
    }


}