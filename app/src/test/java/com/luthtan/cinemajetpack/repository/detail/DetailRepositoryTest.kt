package com.luthtan.cinemajetpack.repository.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.luthtan.cinemajetpack.LiveDataTestUtil
import com.luthtan.cinemajetpack.MockResponseFileReader
import com.luthtan.cinemajetpack.model.NetworkBoundResource
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.local.DetailWithRecommendation
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.remote.ApiResponse
import com.luthtan.cinemajetpack.model.remote.LocalDataSource
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.util.AppExecutors
import com.luthtan.cinemajetpack.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DetailRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val detailRepository = FakeRepositoryDetail(remote, local, appExecutors)

    private val dummyMovieId = 19931
    private val dummyTvShowId = 1399

    @Test
    fun getDetailMovie(){
        val dummyMovieRepository = MutableLiveData<DetailEntity>()
        dummyMovieRepository.value = MockResponseFileReader().getDummyDetail().value?.data
        `when`(local.retrieveMovieFavorite(dummyMovieId)).thenReturn(dummyMovieRepository)

        val detailEntity = LiveDataTestUtil.getValue(detailRepository.getDetailMovie(dummyMovieId))
        verify(local).retrieveMovieFavorite(dummyMovieId)
        assertNotNull(detailEntity.data)
        assertEquals(dummyMovieRepository.value?.title, detailEntity.data?.title)
    }

    @Test
    fun getDetailWithCast(){
        val dummyData = MutableLiveData<DetailWithCast>()
        dummyData.value = MockResponseFileReader().getDummyCast().value?.data
        `when`(local.getDetailWithCast(dummyMovieId)).thenReturn(dummyData)

        val detailWithCast = LiveDataTestUtil.getValue(detailRepository.getDetailWithCast(dummyMovieId))
        verify(local).getDetailWithCast(dummyMovieId)
        assertNotNull(detailWithCast.data)
        assertEquals(detailWithCast.data?.castItemEntity?.get(0)?.name,
            detailWithCast.data?.castItemEntity?.get(0)?.name
        )
    }

    @Test
    fun getDetailWithRecommendation(){
        val dummyData = MutableLiveData<DetailWithRecommendation>()
        dummyData.value = MockResponseFileReader().getDummyRecommendation().value?.data
        `when`(local.getDetailWithRecommendation(dummyMovieId)).thenReturn(dummyData)

        val detailWithRecommendation = LiveDataTestUtil.getValue(detailRepository.getDetailWithRecommendation(dummyMovieId))
        verify(local).getDetailWithRecommendation(dummyMovieId)
        assertNotNull(detailWithRecommendation.data)
        assertEquals(detailWithRecommendation.data?.recommendationItemsEntity?.get(0)?.originalName,
            detailWithRecommendation.data?.recommendationItemsEntity?.get(0)?.originalName
        )
    }

    @Test
    fun getDetailWithTrailer(){
        val dummyData = MutableLiveData<DetailWithTrailer>()
        dummyData.value = MockResponseFileReader().getDummyVideos().value?.data
        `when`(local.getDetailWithTrailer(dummyMovieId)).thenReturn(dummyData)

        val detailWithTrailer = LiveDataTestUtil.getValue(detailRepository.getDetailWithTrailer(dummyMovieId))
        verify(local).getDetailWithTrailer(dummyMovieId)
        assertNotNull(detailWithTrailer.data)
        assertEquals(detailWithTrailer.data?.trailerItemsEntity?.get(0)?.site,
            detailWithTrailer.data?.trailerItemsEntity?.get(0)?.site
        )
    }

    @Test
    fun getDetailTvShow(){
        val dummyMovieRepository = MutableLiveData<DetailEntity>()
        dummyMovieRepository.value = MockResponseFileReader().getDummyTvShowDetail().value?.data
        `when`(local.retrieveTvShowFavorite(dummyTvShowId)).thenReturn(dummyMovieRepository)

        val detailEntity = LiveDataTestUtil.getValue(detailRepository.getDetailTvShow(dummyTvShowId))
        verify(local).retrieveTvShowFavorite(dummyTvShowId)
        assertNotNull(detailEntity.data)
        assertEquals(dummyMovieRepository.value?.title, detailEntity.data?.title)
    }

    @Test
    fun getTvShowDetailWithCast(){
        val dummyData = MutableLiveData<DetailWithCast>()
        dummyData.value = MockResponseFileReader().getDummyTvShowCast().value?.data
        `when`(local.getTvShowDetailWithCast(dummyTvShowId)).thenReturn(dummyData)

        val detailWithCast = LiveDataTestUtil.getValue(detailRepository.getTvShowDetailWithCast(dummyTvShowId))
        verify(local).getTvShowDetailWithCast(dummyTvShowId)
        assertNotNull(detailWithCast.data)
        assertEquals(detailWithCast.data?.castItemEntity?.get(0)?.name,
            detailWithCast.data?.castItemEntity?.get(0)?.name
        )
    }

    @Test
    fun getTvShowDetailWithRecommendation(){
        val dummyData = MutableLiveData<DetailWithRecommendation>()
        dummyData.value = MockResponseFileReader().getDummyTvShowRecommendation().value?.data
        `when`(local.getTvShowDetailWithRecommendation(dummyTvShowId)).thenReturn(dummyData)

        val detailWithRecommendation = LiveDataTestUtil.getValue(detailRepository.getTvShowDetailWithRecommendation(dummyTvShowId))
        verify(local).getTvShowDetailWithRecommendation(dummyTvShowId)
        assertNotNull(detailWithRecommendation.data)
        assertEquals(detailWithRecommendation.data?.recommendationItemsEntity?.get(0)?.originalName,
            detailWithRecommendation.data?.recommendationItemsEntity?.get(0)?.originalName
        )
    }

    @Test
    fun getTvShowDetailWithTrailer(){
        val dummyData = MutableLiveData<DetailWithTrailer>()
        dummyData.value = MockResponseFileReader().getDummyTvShowVideos().value?.data
        `when`(local.getTvShowDetailWithTrailer(dummyTvShowId)).thenReturn(dummyData)

        val detailWithTrailer = LiveDataTestUtil.getValue(detailRepository.getTvShowDetailWithTrailer(dummyTvShowId))
        verify(local).getTvShowDetailWithTrailer(dummyTvShowId)
        assertNotNull(detailWithTrailer.data)
        assertEquals(detailWithTrailer.data?.trailerItemsEntity?.get(0)?.site,
            detailWithTrailer.data?.trailerItemsEntity?.get(0)?.site
        )
    }
}