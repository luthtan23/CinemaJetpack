package com.luthtan.cinemajetpack.repository.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.luthtan.cinemajetpack.MockResponseFileReader
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.local.DetailWithRecommendation
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
import com.luthtan.cinemajetpack.model.remote.LocalDataSource
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.util.AppExecutors
import com.luthtan.cinemajetpack.utils.LiveDataTestUtil
import com.luthtan.cinemajetpack.utils.PagedListUtil
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
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
        Thread {
            val dummyMovieRepository = MutableLiveData<DetailEntity>()
            dummyMovieRepository.value = MockResponseFileReader().getDummyDetail().value?.data
            `when`(local.retrieveMovieFavorite(dummyMovieId)).thenReturn(dummyMovieRepository)

            val detailEntity = LiveDataTestUtil.getValue(detailRepository.getDetailMovie(dummyMovieId))
            verify(local).retrieveMovieFavorite(dummyMovieId)
            verify(remote).getDetailMovie(dummyMovieId)
            assertNotNull(detailEntity.data)
            assertEquals(dummyMovieRepository.value?.title, detailEntity.data?.title)
        }
    }

    @Test
    fun getAllMovieFavoriteList() {
        Thread {
            val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailEntity>
            `when`(local.getAllMovieFavoriteList()).thenReturn(dataSourceFactory)
            detailRepository.getAllMovieFavoriteList()

            val response = MockResponseFileReader().getDummyDetailList().value?.data!!
            val detailEntity = PagedListUtil.mockPagedList(MockResponseFileReader().getDummyDetailList().value?.data!!)
            verify(local).getAllMovieFavoriteList()
            assertNotNull(response)
            assertEquals(response.size.toLong(), detailEntity.size.toLong())
        }
    }

    @Test
    fun getDetailWithCast(){
        Thread {
            val dummyData = MutableLiveData<DetailWithCast>()
            dummyData.value = MockResponseFileReader().getDummyCast().value?.data
            `when`(local.getDetailWithCast(dummyMovieId)).thenReturn(dummyData)

            val detailWithCast = LiveDataTestUtil.getValue(detailRepository.getDetailWithCast(dummyMovieId))
            verify(local).getDetailWithCast(dummyMovieId)
            verify(remote).getDetailCreditsMovie(dummyMovieId)
            assertNotNull(detailWithCast.data)
            assertEquals(detailWithCast.data?.castItemEntity?.get(0)?.name,
                detailWithCast.data?.castItemEntity?.get(0)?.name
            )
        }
    }

    @Test
    fun getDetailWithRecommendation(){
        Thread {
            val dummyData = MutableLiveData<DetailWithRecommendation>()
            dummyData.value = MockResponseFileReader().getDummyRecommendation().value?.data
            `when`(local.getDetailWithRecommendation(dummyMovieId)).thenReturn(dummyData)

            val detailWithRecommendation = LiveDataTestUtil.getValue(detailRepository.getDetailWithRecommendation(dummyMovieId))
            verify(local).getDetailWithRecommendation(dummyMovieId)
            verify(remote).getDetailRecommendationMovie(dummyMovieId)
            assertNotNull(detailWithRecommendation.data)
            assertEquals(detailWithRecommendation.data?.recommendationItemsEntity?.get(0)?.originalName,
                detailWithRecommendation.data?.recommendationItemsEntity?.get(0)?.originalName
            )
        }
    }

    @Test
    fun getDetailWithTrailer(){
        Thread {
            val dummyData = MutableLiveData<DetailWithTrailer>()
            dummyData.value = MockResponseFileReader().getDummyVideos().value?.data
            `when`(local.getDetailWithTrailer(dummyMovieId)).thenReturn(dummyData)

            val detailWithTrailer = LiveDataTestUtil.getValue(detailRepository.getDetailWithTrailer(dummyMovieId))
            verify(local).getDetailWithTrailer(dummyMovieId)
            verify(remote).getDetailVideosMovie(dummyMovieId)
            assertNotNull(detailWithTrailer.data)
            assertEquals(detailWithTrailer.data?.trailerItemsEntity?.get(0)?.site,
                detailWithTrailer.data?.trailerItemsEntity?.get(0)?.site
            )
        }
    }

    @Test
    fun getDetailTvShow(){
        Thread {
            val dummyMovieRepository = MutableLiveData<DetailEntity>()
            dummyMovieRepository.value = MockResponseFileReader().getDummyTvShowDetail().value?.data
            `when`(local.retrieveTvShowFavorite(dummyTvShowId)).thenReturn(dummyMovieRepository)

            val detailEntity = LiveDataTestUtil.getValue(detailRepository.getDetailTvShow(dummyTvShowId))
            verify(local).retrieveTvShowFavorite(dummyTvShowId)
            verify(remote).getDetailTvShow(dummyTvShowId)
            assertNotNull(detailEntity.data)
            assertEquals(dummyMovieRepository.value?.title, detailEntity.data?.title)
        }
    }

    @Test
    fun getAllTvShowFavoriteList() {
        Thread {
            val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailEntity>
            `when`(local.getAllTvShowFavoriteList()).thenReturn(dataSourceFactory)
            detailRepository.getAllTvShowFavoriteList()

            val response = MockResponseFileReader().getDummyTvShowDetailList().value?.data!!
            val detailEntity = PagedListUtil.mockPagedList(MockResponseFileReader().getDummyTvShowDetailList().value?.data!!)
            verify(local).getAllTvShowFavoriteList()
            assertNotNull(response)
            assertEquals(response.size.toLong(), detailEntity.size.toLong())
        }
    }

    @Test
    fun getTvShowDetailWithCast(){
        Thread {
            val dummyData = MutableLiveData<DetailWithCast>()
            dummyData.value = MockResponseFileReader().getDummyTvShowCast().value?.data
            `when`(local.getTvShowDetailWithCast(dummyTvShowId)).thenReturn(dummyData)

            val detailWithCast = LiveDataTestUtil.getValue(detailRepository.getTvShowDetailWithCast(dummyTvShowId))
            verify(local).getTvShowDetailWithCast(dummyTvShowId)
            verify(remote).getDetailCreditsTvShow(dummyTvShowId)
            assertNotNull(detailWithCast.data)
            assertEquals(detailWithCast.data?.castItemEntity?.get(0)?.name,
                detailWithCast.data?.castItemEntity?.get(0)?.name
            )
        }
    }

    @Test
    fun getTvShowDetailWithRecommendation(){
        Thread {
            val dummyData = MutableLiveData<DetailWithRecommendation>()
            dummyData.value = MockResponseFileReader().getDummyTvShowRecommendation().value?.data
            `when`(local.getTvShowDetailWithRecommendation(dummyTvShowId)).thenReturn(dummyData)

            val detailWithRecommendation = LiveDataTestUtil.getValue(detailRepository.getTvShowDetailWithRecommendation(dummyTvShowId))
            verify(local).getTvShowDetailWithRecommendation(dummyTvShowId)
            verify(remote).getDetailRecommendationTvShow(dummyTvShowId)
            assertNotNull(detailWithRecommendation.data)
            assertEquals(detailWithRecommendation.data?.recommendationItemsEntity?.get(0)?.originalName,
                detailWithRecommendation.data?.recommendationItemsEntity?.get(0)?.originalName
            )
        }
    }

    @Test
    fun getTvShowDetailWithTrailer(){
        Thread {
            val dummyData = MutableLiveData<DetailWithTrailer>()
            dummyData.value = MockResponseFileReader().getDummyTvShowVideos().value?.data
            `when`(local.getTvShowDetailWithTrailer(dummyTvShowId)).thenReturn(dummyData)

            val detailWithTrailer = LiveDataTestUtil.getValue(detailRepository.getTvShowDetailWithTrailer(dummyTvShowId))
            verify(local).getTvShowDetailWithTrailer(dummyTvShowId)
            verify(remote).getDetailVideosTvShow(dummyTvShowId)
            assertNotNull(detailWithTrailer.data)
            assertEquals(detailWithTrailer.data?.trailerItemsEntity?.get(0)?.site,
                detailWithTrailer.data?.trailerItemsEntity?.get(0)?.site
            )
        }
    }
}