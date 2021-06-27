package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.luthtan.cinemajetpack.model.bean.local.*
import com.luthtan.cinemajetpack.model.local.db.dao.MovieDao
import com.luthtan.cinemajetpack.model.local.db.dao.TvShowDao
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(private val movieDao: MovieDao, private val tvShowDao: TvShowDao) {

    fun getAllMovieFavorite(): LiveData<List<DetailEntity>> = movieDao.getAllMovieFavorite()

    fun getAllMovieFavoriteList(): DataSource.Factory<Int, DetailEntity> = movieDao.getAllMovieFavoriteList()

    fun addMovieFavorite(detailEntity: DetailEntity) = movieDao.insertMovie(detailEntity)

    fun retrieveMovieFavorite(id: Int): LiveData<DetailEntity> = movieDao.retrieveMovieFavorite(id)

    fun deleteMovieFavorite(id: Int) {
        EspressIdlingResources.increment()
        GlobalScope.launch {
            movieDao.deleteMovieFavorite(id)
            EspressIdlingResources.decrement()
        }
    }

    fun updateMovieFavorite(detailEntity: DetailEntity, newState: Boolean) {
        detailEntity.isMovieFavorite = newState
        movieDao.updateMovieFavorite(detailEntity)
    }

    fun insertDetailWithCastList(castItem: List<CastItemEntity>) =
        movieDao.insertGetDetailWithCastList(castItem)

    fun insertDetailWithRecommendationList(recommendationEntity: List<RecommendationItemsEntity>) =
        movieDao.insertGetDetailWithRecommendationList(recommendationEntity)

    fun insertDetailWithTrailerList(trailerItemsEntity: List<TrailerItemsEntity>) =
        movieDao.insertGetDetailWithTrailer(trailerItemsEntity)

    fun getDetailWithCast(detailId: Int): LiveData<DetailWithCast> =
        movieDao.getDetailWithCast(detailId)

    fun getDetailWithRecommendation(detailId: Int): LiveData<DetailWithRecommendation> =
        movieDao.getDetailWithRecommendation(detailId)

    fun getDetailWithTrailer(detailId: Int): LiveData<DetailWithTrailer> =
        movieDao.getDetailWithTrailer(detailId)


    fun getAllTvShowFavorite(): LiveData<List<DetailEntity>> = tvShowDao.getAllTvShowFavorite()

    fun getAllTvShowFavoriteList(): DataSource.Factory<Int, DetailEntity> =
        tvShowDao.getAllTvShowFavoriteList()

    fun addTvShowFavorite(detailEntity: DetailEntity) = tvShowDao.insertTvShow(detailEntity)

    fun retrieveTvShowFavorite(id: Int): LiveData<DetailEntity> {
        return tvShowDao.retrieveTvShowFavorite(id)
    }

    fun deleteTvShowFavorite(id: Int) {
        EspressIdlingResources.increment()
        GlobalScope.launch {
            tvShowDao.deleteTvShowFavorite(id)
            EspressIdlingResources.decrement()
        }
    }

    fun updateTvShowFavorite(detailEntity: DetailEntity, newState: Boolean) {
        detailEntity.isTvShowFavorite = newState
        tvShowDao.updateTvShowFavorite(detailEntity)
    }

    fun insertTvShowDetailWithCastList(castItem: List<CastItemEntity>) =
        tvShowDao.insertGetDetailWithCastList(castItem)

    fun insertTvShowDetailWithRecommendationList(recommendationEntity: List<RecommendationItemsEntity>) =
        tvShowDao.insertGetDetailWithRecommendationList(recommendationEntity)

    fun insertTvShowDetailWithTrailerList(trailerItemsEntity: List<TrailerItemsEntity>) =
        tvShowDao.insertGetDetailWithTrailer(trailerItemsEntity)

    fun getTvShowDetailWithCast(detailId: Int): LiveData<DetailWithCast> =
        tvShowDao.getDetailWithCast(detailId)

    fun getTvShowDetailWithRecommendation(detailId: Int): LiveData<DetailWithRecommendation> =
        tvShowDao.getDetailWithRecommendation(detailId)

    fun getTvShowDetailWithTrailer(detailId: Int): LiveData<DetailWithTrailer> =
        tvShowDao.getDetailWithTrailer(detailId)

}