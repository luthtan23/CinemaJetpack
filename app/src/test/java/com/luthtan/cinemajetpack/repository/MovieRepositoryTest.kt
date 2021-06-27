package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.repository.movie.MovieRepository
import com.luthtan.cinemajetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
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

    private val _moviePopularResponse = MutableLiveData<Resource<MovieResponse>>()
    private var _moviePopularResponseDummy = MutableLiveData<Resource<MovieResponse>>()

    @Test
    fun getPopularMovie() {
        Thread {
            val moviePopular =
                fakeRepository.getPopularMovie(_moviePopularResponseDummy).value?.data
            `when`(movieRepository.getPopularMovie(_moviePopularResponse).value?.data).thenReturn(
                moviePopular
            )
            verify(movieRepository).getPopularMovie(_moviePopularResponse).value?.data
            assertNotNull(moviePopular)
            assertEquals(
                moviePopular?.results?.size,
                movieRepository.getPopularMovie(
                    _moviePopularResponse
                ).value?.data?.results?.size
            )
        }
    }
}