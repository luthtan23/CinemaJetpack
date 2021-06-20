package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class MovieRepositoryTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private val fakeRepository = FakeRepository()

    private val _moviePopularResponse = MutableLiveData<MovieResponse>()
    private var _moviePopularResponseDummy = MutableLiveData<MovieResponse>()
    private val _errorResponse = MutableLiveData<String>()
    private var _errorResponseDummy = MutableLiveData<String>()

    @Test
    fun getPopularMovie() {
        GlobalScope.launch {
            val moviePopular =
                fakeRepository.getPopularMovie(_moviePopularResponseDummy, _errorResponseDummy)
            `when`(
                movieRepository.getPopularMovie(
                    _moviePopularResponse,
                    _errorResponse
                )
            ).thenReturn(moviePopular)
            verify(movieRepository).getPopularMovie(_moviePopularResponse, _errorResponse)
            assertNotNull(moviePopular)
            assertEquals(
                moviePopular.value?.results?.size,
                movieRepository.getPopularMovie(
                    _moviePopularResponse,
                    _errorResponse
                ).value?.results?.size
            )
        }

    }
}