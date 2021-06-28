package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepository
import com.luthtan.cinemajetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class TvShowRepositoryTest {

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    private val fakeRepository = FakeRepositoryTvShow()

    private val _tvShowPopularResponse = MutableLiveData<Resource<TvShowResponse>>()
    private var _tvShowPopularResponseDummy = MutableLiveData<Resource<TvShowResponse>>()

    @Test
    fun getPopularTvShow() {
        Thread {
            val tvShowPopular =
                fakeRepository.getPopularTvShow(_tvShowPopularResponseDummy).value?.data
            Mockito.`when`(tvShowRepository.getPopularTvShow(_tvShowPopularResponse).value?.data)
                .thenReturn(tvShowPopular)
            verify(tvShowRepository).getPopularTvShow(_tvShowPopularResponse).value?.data
            assertNotNull(tvShowPopular)
            assertEquals(
                tvShowPopular?.results?.size,
                tvShowRepository.getPopularTvShow(
                    _tvShowPopularResponse
                ).value?.data?.results?.size
            )
        }
    }


}