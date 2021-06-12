package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.repository.TvShowRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<TvShowResponse>

    private lateinit var tvShowViewModel: TvShowViewModel

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShowPopularResponse() {
        val tvShowResponse = tvShowViewModel.getTvShowResponse()
        assertNotNull(tvShowResponse)
        tvShowViewModel.tvShowPopularResponse.observeForever(observer)
    }

    @Test
    fun getTvShowTopRatedResponse() {
    }

    @Test
    fun getTvShowNowPlayingResponse() {
    }

    @Test
    fun getTvShowUpcomingResponse() {
    }

    @Test
    fun getErrorResponse() {
    }

    @Test
    fun getTvShowResponse() {
    }
}