package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.MovieRepository
import com.luthtan.cinemajetpack.repository.movie.MovieRepositoryListener
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest : MovieRepositoryListener {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val _moviePopularResponse = MutableLiveData<MovieResponse>()
    private var _moviePopularResponseDummy = MutableLiveData<MovieResponse>()
    private val _errorResponse = MutableLiveData<String>()
    private var _errorResponseDummy = MutableLiveData<String>()

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

        GlobalScope.launch {
            val dummyData = getPopularMovie(_moviePopularResponseDummy, _errorResponseDummy)
            
            `when`(
                movieRepository.getPopularMovie(
                    _moviePopularResponse,
                    _errorResponse
                )
            ).thenReturn(dummyData)
            val movieResponseViewModel = movieViewModel.moviePopularResponse.value
            verify(movieRepository).getNowPlayingMovie(_moviePopularResponse, _errorResponse)
            assertNotNull(movieResponseViewModel)
            assertEquals(20, movieResponseViewModel?.results?.size)

            movieViewModel.moviePopularResponse.observeForever(observer)
            verify(observer).onChanged(dummyData.value)
        }


    }

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