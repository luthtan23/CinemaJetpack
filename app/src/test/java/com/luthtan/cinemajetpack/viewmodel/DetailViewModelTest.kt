package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.repository.DetailRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailViewModel: DetailViewModel

    @Mock
    private lateinit var detailRepository: DetailRepository

    @Mock
    private lateinit var observer: Observer<DetailResponse>

    @Mock
    private lateinit var creditObserver: Observer<CreditResponse>

    @Mock
    private lateinit var recommendationObserver: Observer<RecommendationResponse>

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(detailRepository)
    }

    @Test
    fun getDetailResponse() {
        val detailResponse = detailViewModel.getDetailMovie(19913)
        assertNotNull(detailResponse)
        detailViewModel.detailResponse.observeForever(observer)
    }

    @Test
    fun getCreditResponse() {
        val creditResponse = detailViewModel.creditResponse
        assertNotNull(creditResponse)
        detailViewModel.creditResponse.observeForever(creditObserver)
    }

    @Test
    fun getRecommendationResponse() {
        val recommendationResponse = detailViewModel.recommendationResponse
        assertNotNull(recommendationResponse)
        detailViewModel.recommendationResponse.observeForever(recommendationObserver)
    }

    @Test
    fun getTrailerResponse() {
    }

    @Test
    fun getErrorResponse() {
    }

    @Test
    fun getDetailMovie() {
    }

    @Test
    fun getDetailTvShow() {
    }

    @Test
    fun getDetailVideoMovie() {
    }

    @Test
    fun getDetailVideoTvShow() {
    }
}