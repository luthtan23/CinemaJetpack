package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepository
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepositorySource
import com.luthtan.cinemajetpack.vo.Resource
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
class TvShowViewModelTest : TvShowRepositorySource {

    private val _tvShowPopularResponse = MutableLiveData<Resource<TvShowResponse>>()
    private val _tvShowPopularResponseDummy = MutableLiveData<Resource<TvShowResponse>>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<TvShowResponse>>

    private lateinit var tvShowViewModel: TvShowViewModel

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShowPopularResponse() {
        /*GlobalScope.launch {
            val dummyData = getPopularTvShow(_tvShowPopularResponseDummy).value

            `when`(tvShowRepository.getPopularTvShow(_tvShowPopularResponse).value).thenReturn(
                dummyData
            )
            val tvShowResponseViewModel = tvShowViewModel.tvShowPopularResponse.value?.data
            verify(tvShowRepository).getPopularTvShow(_tvShowPopularResponse)
            assertNotNull(tvShowResponseViewModel)
            Assert.assertEquals(20, tvShowResponseViewModel?.results?.size)

            tvShowViewModel.tvShowPopularResponse.observeForever(observer)
            verify(observer).onChanged(dummyData)
        }*/
    }

    override fun getPopularTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return tvShowResponse
    }

    override fun getNowPlayingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return tvShowResponse
    }

    override fun getTopRatedTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return tvShowResponse
    }

    override fun getUpcomingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return tvShowResponse
    }

}