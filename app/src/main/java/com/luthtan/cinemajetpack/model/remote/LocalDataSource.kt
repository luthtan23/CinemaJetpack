package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.local.db.dao.MovieDao
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(private val movieDao: MovieDao) {

    fun addMovieFavorite(detailResponse: DetailResponse) {
        EspressIdlingResources.increment()
        GlobalScope.launch {
            try {
                val gson = Gson().toJson(detailResponse)
                val movieEntity = Gson().fromJson(gson, MovieEntity::class.java)
                movieEntity.isMovieFavorite = !movieEntity.isMovieFavorite
                movieDao.insertMovie(movieEntity)
                EspressIdlingResources.decrement()
            } catch (exception: java.lang.Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun getMovieFavorite() : LiveData<List<MovieEntity>> = movieDao.getAllMovieFavorite()

    fun retrieveMovieFavorite(id: Int): LiveData<MovieEntity> = movieDao.retrieveMovieFavorite(id)

    fun deleteMovieFavorite(id: Int) { movieDao.deleteMovieFavorite(id) }

    fun getMovieFavoriteCastItem(): LiveData<List<CastItem>> = movieDao.getMovieFavoriteCastItem()

}