package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.MockResponseFileReader
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
class TvShowViewModelTest {

    private val _tvShowPopularResponse = MutableLiveData<Resource<TvShowResponse>>()

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
        Thread {
            val dummyData = MockResponseFileReader().getDummyTvShowResponse()

            `when`(tvShowRepository.getPopularTvShow(_tvShowPopularResponse).value).thenReturn(
                dummyData.value
            )
            val tvShowResponseViewModel = tvShowViewModel.tvShowPopularResponse.value?.data
            verify(tvShowRepository).getPopularTvShow(_tvShowPopularResponse)
            assertNotNull(tvShowResponseViewModel)
            Assert.assertEquals(20, tvShowResponseViewModel?.results?.size)

            tvShowViewModel.tvShowPopularResponse.observeForever(observer)
            verify(observer).onChanged(dummyData.value)
        }
    }


}