package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.luthtan.cinemajetpack.MockResponseFileReader
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.local.DetailWithRecommendation
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
import com.luthtan.cinemajetpack.repository.detail.DetailRepository
import com.luthtan.cinemajetpack.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

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

    @Mock
    private lateinit var movieFavoriteListObserver: Observer<PagedList<DetailEntity>>

    @Mock
    private lateinit var tvShowFavoriteListObserver: Observer<PagedList<DetailEntity>>

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(detailRepository)
    }

    private val dummyMovieId = 19931
    private val dummyTvShowId = 1399

    @Test
    fun getDetailResponse() {
        Thread {
            val detailResponse = MockResponseFileReader().getDummyDetail()
            val detailEntity: LiveData<Resource<DetailEntity>> = detailResponse

            `when`(detailRepository.getDetailMovie(dummyMovieId)).thenReturn(detailEntity)
            val detailEntities = detailViewModel.detailMovieFavorite.value?.data
            verify(detailRepository).getDetailMovie(dummyMovieId)
            assertNotNull(detailEntities)

            detailViewModel.detailMovieFavorite.observeForever(observer)
            verify(observer).onChanged(detailResponse.value)
            assertNotNull(detailResponse)
            detailViewModel.detailMovieFavorite.observeForever(observer)
        }
    }

    @Test
    fun getTvShowDetailResponse() {
        Thread {
            val detailResponse = MockResponseFileReader().getDummyTvShowDetail()
            val detailEntity: LiveData<Resource<DetailEntity>> = detailResponse

            `when`(detailRepository.getDetailTvShow(dummyTvShowId)).thenReturn(detailEntity)
            val detailEntities = detailViewModel.detailTvShowFavorite.value?.data
            verify(detailRepository).getDetailTvShow(dummyTvShowId)
            assertNotNull(detailEntities)

            detailViewModel.detailTvShowFavorite.observeForever(observer)
            verify(observer).onChanged(detailResponse.value)
            assertNotNull(detailResponse)
            detailViewModel.detailTvShowFavorite.observeForever(observer)
        }
    }

    class PagedTestDataSources private constructor(private val items: List<DetailEntity>) : PositionalDataSource<DetailEntity>() {
        companion object {
            fun snapshot(items: List<DetailEntity> = listOf()): PagedList<DetailEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<DetailEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<DetailEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

    @Test
    fun getAllMovieFavoriteList() {
        Thread {
            val dummyData = PagedTestDataSources.snapshot(MockResponseFileReader().getDummyDetailList().value?.data!!)
            val expected = MutableLiveData<Resource<PagedList<DetailEntity>>>()
            expected.value = Resource.success(dummyData)

            `when`(detailRepository.getAllMovieFavoriteList().value).thenReturn(expected.value!!.data)

            detailViewModel.getAllMovieFavorite().observeForever(movieFavoriteListObserver)
            verify(movieFavoriteListObserver).onChanged(expected.value!!.data)

            val expectedValue = expected.value
            val actualValue = detailViewModel.getAllMovieFavorite().value
            assertEquals(expectedValue, actualValue)
            assertEquals(expectedValue?.data, actualValue)
            assertEquals(expectedValue?.data?.size, actualValue?.size)
        }
    }

    @Test
    fun getAllTvShowFavoriteList() {
        Thread {
            val dummyData = PagedTestDataSources.snapshot(MockResponseFileReader().getDummyTvShowDetailList().value?.data!!)
            val expected = MutableLiveData<Resource<PagedList<DetailEntity>>>()
            expected.value = Resource.success(dummyData)

            `when`(detailRepository.getAllTvShowFavoriteList().value).thenReturn(expected.value!!.data)

            detailViewModel.getAllTvShowFavorite().observeForever(tvShowFavoriteListObserver)
            verify(tvShowFavoriteListObserver).onChanged(expected.value!!.data)

            val expectedValue = expected.value
            val actualValue = detailViewModel.getAllTvShowFavorite().value
            assertEquals(expectedValue, actualValue)
            assertEquals(expectedValue?.data, actualValue)
            assertEquals(expectedValue?.data?.size, actualValue?.size)
        }
    }

   @Test
   fun getDetailWithCast() {
       Thread {
           val castResponse = MockResponseFileReader().getDummyCast()
           val castEntity: LiveData<Resource<DetailWithCast>> = castResponse

           `when`(detailRepository.getDetailWithCast(dummyMovieId)).thenReturn(castEntity)
           val castEntities = detailViewModel.detailWithCast.value?.data
           verify(detailRepository).getDetailWithCast(dummyMovieId)
           assertNotNull(castEntities)

           detailViewModel.detailWithCast.observeForever(creditObserver)
           verify(creditObserver).onChanged(castResponse.value)
           assertNotNull(castResponse)
           detailViewModel.detailWithCast.observeForever(creditObserver)
       }
   }

    @Test
    fun getTvShowDetailWithCast() {
        Thread {
            val castResponse = MockResponseFileReader().getDummyTvShowCast()
            val castEntity: LiveData<Resource<DetailWithCast>> = castResponse

            `when`(detailRepository.getTvShowDetailWithCast(dummyTvShowId)).thenReturn(castEntity)
            val castEntities = detailViewModel.detailWithCastTvShow.value?.data
            verify(detailRepository).getTvShowDetailWithCast(dummyTvShowId)
            assertNotNull(castEntities)

            detailViewModel.detailWithCastTvShow.observeForever(creditObserver)
            verify(creditObserver).onChanged(castResponse.value)
            assertNotNull(castResponse)
            detailViewModel.detailWithCastTvShow.observeForever(creditObserver)
        }
    }

    @Test
    fun getDetailWithRecommendation() {
        Thread {
            val recommendationResponse = MockResponseFileReader().getDummyRecommendation()
            val recommendationItemsEntity: LiveData<Resource<DetailWithRecommendation>> = recommendationResponse

            `when`(detailRepository.getDetailWithRecommendation(dummyMovieId)).thenReturn(recommendationItemsEntity)
            val recommendationEntities = detailViewModel.detailWithRecommendation.value?.data
            verify(detailRepository).getDetailWithRecommendation(dummyMovieId)
            assertNotNull(recommendationEntities)

            detailViewModel.detailWithRecommendation.observeForever(recommendationObserver)
            verify(recommendationObserver).onChanged(recommendationResponse.value)
            assertNotNull(recommendationResponse)
            detailViewModel.detailWithRecommendation.observeForever(recommendationObserver)
        }
    }

    @Test
    fun getTvShowDetailWithRecommendation() {
        Thread {
            val recommendationResponse = MockResponseFileReader().getDummyTvShowRecommendation()
            val recommendationItemsEntity: LiveData<Resource<DetailWithRecommendation>> = recommendationResponse

            `when`(detailRepository.getTvShowDetailWithRecommendation(dummyTvShowId)).thenReturn(recommendationItemsEntity)
            val recommendationEntities = detailViewModel.detailWithRecommendation.value?.data
            verify(detailRepository).getTvShowDetailWithRecommendation(dummyTvShowId)
            assertNotNull(recommendationEntities)

            detailViewModel.detailWithRecommendationTvShow.observeForever(recommendationObserver)
            verify(recommendationObserver).onChanged(recommendationResponse.value)
            assertNotNull(recommendationResponse)
            detailViewModel.detailWithRecommendationTvShow.observeForever(recommendationObserver)
        }
    }

    @Test
    fun getDetailWithTrailer() {
        Thread {
            val videosResponse = MockResponseFileReader().getDummyVideos()
            val trailerItemsEntity: LiveData<Resource<DetailWithTrailer>> = videosResponse

            `when`(detailRepository.getDetailWithTrailer(dummyMovieId)).thenReturn(trailerItemsEntity)
            val trailerEntities = detailViewModel.detailWithTrailer.value?.data
            verify(detailRepository).getDetailWithTrailer(dummyMovieId)
            assertNotNull(trailerEntities)

            detailViewModel.detailWithTrailer.observeForever(trailerObserver)
            verify(trailerObserver).onChanged(videosResponse.value)
            assertNotNull(videosResponse)
            detailViewModel.detailWithTrailer.observeForever(trailerObserver)
        }
    }

    @Test
    fun getTvShowDetailWithTrailer() {
        Thread {
            val videosResponse = MockResponseFileReader().getDummyTvShowVideos()
            val trailerItemsEntity: LiveData<Resource<DetailWithTrailer>> = videosResponse

            `when`(detailRepository.getTvShowDetailWithTrailer(dummyTvShowId)).thenReturn(trailerItemsEntity)
            val trailerEntities = detailViewModel.detailWithTrailerTvShow.value?.data
            verify(detailRepository).getTvShowDetailWithTrailer(dummyTvShowId)
            assertNotNull(trailerEntities)

            detailViewModel.detailWithTrailerTvShow.observeForever(trailerObserver)
            verify(trailerObserver).onChanged(videosResponse.value)
            assertNotNull(videosResponse)
            detailViewModel.detailWithTrailerTvShow.observeForever(trailerObserver)
        }
    }

    @Test
    fun setMovieFavorite() {
        Thread {
            val movieFavorite = detailRepository.getDetailMovie(dummyMovieId).value?.data

            //add or delete movie favorite
            detailRepository.updateMovieFavorite(movieFavorite!!, !movieFavorite.isMovieFavorite)
            val dummyData = PagedTestDataSources.snapshot(MockResponseFileReader().getDummyDetailList().value?.data!!)
            val expected = MutableLiveData<Resource<PagedList<DetailEntity>>>()
            expected.value = Resource.success(dummyData)

            `when`(detailRepository.getAllMovieFavoriteList().value).thenReturn(expected.value!!.data)

            detailViewModel.getAllMovieFavorite().observeForever(movieFavoriteListObserver)
            verify(movieFavoriteListObserver).onChanged(expected.value!!.data)

            val expectedValue = expected.value
            val actualValue = detailViewModel.getAllMovieFavorite().value
            assertEquals(expectedValue, actualValue)
            assertEquals(expectedValue?.data, actualValue)
            assertEquals(expectedValue?.data?.size, actualValue?.size)
        }
    }

    @Test
    fun setTvShowFavorite() {
        Thread {
            val tvShowFavorite = detailRepository.getDetailTvShow(dummyTvShowId).value?.data

            //add or delete tv show favorite
            detailRepository.updateTvShowFavorite(tvShowFavorite!!, !tvShowFavorite.isTvShowFavorite)
            val dummyData = PagedTestDataSources.snapshot(MockResponseFileReader().getDummyTvShowDetailList().value?.data!!)
            val expected = MutableLiveData<Resource<PagedList<DetailEntity>>>()
            expected.value = Resource.success(dummyData)

            `when`(detailRepository.getAllTvShowFavoriteList().value).thenReturn(expected.value!!.data)

            detailViewModel.getAllTvShowFavorite().observeForever(tvShowFavoriteListObserver)
            verify(tvShowFavoriteListObserver).onChanged(expected.value!!.data)

            val expectedValue = expected.value
            val actualValue = detailViewModel.getAllTvShowFavorite().value
            assertEquals(expectedValue, actualValue)
            assertEquals(expectedValue?.data, actualValue)
            assertEquals(expectedValue?.data?.size, actualValue?.size)
        }
    }

}