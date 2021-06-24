package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.LiveData
import com.luthtan.cinemajetpack.model.bean.local.CastItemEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.local.db.dao.MovieDao
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(private val movieDao: MovieDao) {

    fun addMovieFavorite(detailEntity: DetailEntity) = movieDao.insertMovie(detailEntity)

    fun getMovieFavorite() : LiveData<List<DetailEntity>> = movieDao.getAllMovieFavorite()

    fun retrieveMovieFavorite(id: Int): LiveData<DetailEntity> {
        return movieDao.retrieveMovieFavorite(id)
    }

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

    fun castItemList(id: Int) = movieDao.getDetailWithCastList(id)

    fun insertCastItemList(castItem: List<CastItemEntity>) = movieDao.insertCastItemList(castItem)

    fun getDetailWithCast(detailId: Int): LiveData<DetailWithCast> = movieDao.getDetailWithCast(detailId)

}