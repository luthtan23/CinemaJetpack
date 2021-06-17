package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.repository.TvShowRepository
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepositoryListener
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest : TvShowRepositoryListener {

    private val _tvShowPopularResponse = MutableLiveData<TvShowResponse>()
    private val _tvShowPopularResponseDummy = MutableLiveData<TvShowResponse>()
    private val _errorResponse = MutableLiveData<String>()
    private val _errorResponseDummy = MutableLiveData<String>()

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

        GlobalScope.launch {
            val dummyData = getPopularTvShow(_tvShowPopularResponseDummy, _errorResponseDummy)

            val tvShowResponse = tvShowViewModel.tvShowPopularResponse.value
            assertNotNull(tvShowResponse)
            `when`(
                tvShowRepository.getPopularTvShow(
                    _tvShowPopularResponse,
                    _errorResponse
                )
            ).thenReturn(dummyData)
            val tvShowResponseViewModel = tvShowViewModel.tvShowPopularResponse.value
            verify(tvShowRepository).getPopularTvShow(_tvShowPopularResponse, _errorResponse)
            assertNotNull(tvShowResponseViewModel)
            Assert.assertEquals(20, tvShowResponseViewModel?.results?.size)

            tvShowViewModel.tvShowPopularResponse.observeForever(observer)
            verify(observer).onChanged(dummyData.value)
        }
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

    override suspend fun getPopularTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        return tvShowResponse
    }

    override suspend fun getNowPlayingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        return tvShowResponse
    }

    override suspend fun getTopRatedTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        return tvShowResponse
    }

    override suspend fun getUpcomingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        return tvShowResponse
    }
}