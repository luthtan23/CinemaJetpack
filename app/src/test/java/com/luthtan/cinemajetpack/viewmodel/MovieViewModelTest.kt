package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.espresso.IdlingRegistry
import com.luthtan.cinemajetpack.MockResponseFileReader
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.movie.MovieRepository
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import com.luthtan.cinemajetpack.vo.Resource
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val _moviePopularResponse = MutableLiveData<Resource<MovieResponse>>()

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
        Thread {
            val dummyData = MockResponseFileReader().getDummyMovieResponse()
            `when`(movieRepository.getPopularMovie(_moviePopularResponse).value).thenReturn(
                dummyData.value
            )
            val movieResponseViewModel = movieViewModel.moviePopularResponse.value?.data
            verify(
                movieRepository,
                timeout(2000)
            ).getNowPlayingMovie(_moviePopularResponse).value?.data
            assertNotNull(movieResponseViewModel)
            assertEquals(20, movieResponseViewModel?.results?.size)

            movieViewModel.moviePopularResponse.observeForever(observer)
            verify(observer).onChanged(dummyData.value)
        }
    }

}