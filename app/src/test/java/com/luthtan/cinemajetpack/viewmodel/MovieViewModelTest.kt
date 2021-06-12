package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.MovieRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val _moviePopularResponse = MutableLiveData<MovieResponse>()
    private val _errorResponse = MutableLiveData<String>()

    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<MovieResponse>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMoviePopularResponse() {
        val movieResponse = movieViewModel.getMovieResponse()
        assertNotNull(movieResponse)
        movieViewModel.moviePopularResponse.observeForever(observer)
    }

    @Test
    fun getMovieTopRatedResponse() {
    }

    @Test
    fun getMovieNowPlayingResponse() {
    }

    @Test
    fun getMovieUpcomingResponse() {
    }

    @Test
    fun getErrorResponse() {
    }

    @Test
    fun getMovieResponse() {
    }
}