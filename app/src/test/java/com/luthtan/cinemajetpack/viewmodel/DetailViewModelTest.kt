package com.luthtan.cinemajetpack.viewmodel

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.InstrumentationRegistry
import com.luthtan.cinemajetpack.MockResponseFileReader
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.local.DetailWithRecommendation
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.repository.detail.DetailRepository
import com.luthtan.cinemajetpack.util.DummyDataJson
import com.luthtan.cinemajetpack.vo.Resource
import junit.framework.Assert
import kotlinx.coroutines.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailViewModel: DetailViewModel

    @Mock
    private lateinit var detailRepository: DetailRepository

    @Mock
    private lateinit var observer: Observer<Resource<DetailEntity>>

    @Mock
    private lateinit var creditObserver: Observer<Resource<DetailWithCast>>

    @Mock
    private lateinit var recommendationObserver: Observer<Resource<DetailWithRecommendation>>

    @Mock
    private lateinit var trailerObserver: Observer<Resource<DetailWithTrailer>>

    private lateinit var mockWebServer: MockWebServer



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        detailViewModel = DetailViewModel(detailRepository)
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }



    @Test
    fun getDetailResponse() {
        Thread {
            val detailResponse = MockResponseFileReader().getDummyDetail()
            val detailEntity: LiveData<Resource<DetailEntity>> = detailResponse

            `when`(detailRepository.getDetailMovie(19931)).thenReturn(detailEntity)
            val detailEntities = detailViewModel.detailMovieFavorite.value?.data
            verify(detailRepository).getDetailMovie(19931)
            assertNotNull(detailEntities)

            detailViewModel.detailMovieFavorite.observeForever(observer)
            verify(observer).onChanged(detailResponse.value)
            assertNotNull(detailResponse)
            detailViewModel.detailMovieFavorite.observeForever(observer)
        }
    }

   @Test
   fun getDetailWithCast() {
       Thread {
           val castResponse = MockResponseFileReader().getDummyCast()
           val castEntity: LiveData<Resource<DetailWithCast>> = castResponse

           `when`(detailRepository.getDetailWithCast(19931)).thenReturn(castEntity)
           val castEntities = detailViewModel.detailWithCast.value?.data
           verify(detailRepository).getDetailWithCast(19931)
           assertNotNull(castEntities)

           detailViewModel.detailWithCast.observeForever(creditObserver)
           verify(creditObserver).onChanged(castResponse.value)
           assertNotNull(castResponse)
           detailViewModel.detailWithCast.observeForever(creditObserver)
       }
   }

    @Test
    fun getDetailWithRecommendation() {
        Thread {
            val recommendationResponse = MockResponseFileReader().getDummyRecommendation()
            val recommendationItemsEntity: LiveData<Resource<DetailWithRecommendation>> = recommendationResponse

            `when`(detailRepository.getDetailWithRecommendation(19931)).thenReturn(recommendationItemsEntity)
            val recommendationEntities = detailViewModel.detailWithRecommendation.value?.data
            verify(detailRepository).getDetailWithRecommendation(19931)
            assertNotNull(recommendationEntities)

            detailViewModel.detailWithRecommendation.observeForever(recommendationObserver)
            verify(recommendationObserver).onChanged(recommendationResponse.value)
            assertNotNull(recommendationResponse)
            detailViewModel.detailWithRecommendation.observeForever(recommendationObserver)
        }
    }

    @Test
    fun getDetailWithTrailer() {
        Thread {
            val videosResponse = MockResponseFileReader().getDummyVideos()
            val trailerItemsEntity: LiveData<Resource<DetailWithTrailer>> = videosResponse

            `when`(detailRepository.getDetailWithTrailer(19931)).thenReturn(trailerItemsEntity)
            val trailerEntities = detailViewModel.detailWithTrailer.value?.data
            verify(detailRepository).getDetailWithTrailer(19931)
            assertNotNull(trailerEntities)

            detailViewModel.detailWithTrailer.observeForever(trailerObserver)
            verify(trailerObserver).onChanged(videosResponse.value)
            assertNotNull(videosResponse)
            detailViewModel.detailWithTrailer.observeForever(trailerObserver)
        }
    }

}