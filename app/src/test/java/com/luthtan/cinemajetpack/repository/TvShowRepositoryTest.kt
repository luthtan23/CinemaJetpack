package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class TvShowRepositoryTest {

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    private val fakeRepository = FakeRepositoryTvShow()

    private val _tvShowPopularResponse = MutableLiveData<TvShowResponse>()
    private var _tvShowPopularResponseDummy = MutableLiveData<TvShowResponse>()
    private val _errorResponse = MutableLiveData<String>()
    private var _errorResponseDummy = MutableLiveData<String>()


    @Test
    fun getPopularTvShow() {
        GlobalScope.launch {
            val tvShowPopular =
                fakeRepository.getPopularTvShow(_tvShowPopularResponseDummy, _errorResponseDummy)
            Mockito.`when`(
                tvShowRepository.getPopularTvShow(
                    _tvShowPopularResponse,
                    _errorResponse
                )
            ).thenReturn(tvShowPopular)
            verify(tvShowRepository).getPopularTvShow(_tvShowPopularResponse, _errorResponse)
            assertNotNull(tvShowPopular)
            assertEquals(
                tvShowPopular.value?.results?.size,
                tvShowRepository.getPopularTvShow(
                    _tvShowPopularResponse,
                    _errorResponse
                ).value?.results?.size
            )
        }
    }


}