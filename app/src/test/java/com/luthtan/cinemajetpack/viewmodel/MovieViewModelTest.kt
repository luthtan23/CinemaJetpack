package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.movie.MovieRepository
import com.luthtan.cinemajetpack.repository.movie.MovieRepositorySource
import com.luthtan.cinemajetpack.vo.Resource
import com.nhaarman.mockitokotlin2.timeout
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
class MovieViewModelTest : MovieRepositorySource {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val _moviePopularResponse = MutableLiveData<Resource<MovieResponse>>()
    private var _moviePopularResponseDummy = MutableLiveData<Resource<MovieResponse>>()

    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieResponse>>


    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMoviePopularResponse() {
        GlobalScope.launch {
            val dummyData = getPopularMovie(_moviePopularResponseDummy).value
            `when`(movieRepository.getPopularMovie(_moviePopularResponse).value).thenReturn(
                dummyData
            )
            val movieResponseViewModel = movieViewModel.moviePopularResponse.value?.data
            verify(
                movieRepository,
                timeout(2000)
            ).getNowPlayingMovie(_moviePopularResponse).value?.data
            assertNotNull(movieResponseViewModel)
            assertEquals(20, movieResponseViewModel?.results?.size)

            movieViewModel.moviePopularResponse.observeForever(observer)
            verify(observer).onChanged(dummyData)
        }
    }

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