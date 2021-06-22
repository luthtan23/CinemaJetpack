package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.LiveData
import com.luthtan.cinemajetpack.model.bean.local.MovieItemDB
import com.luthtan.cinemajetpack.model.local.db.dao.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovieFavorite(): LiveData<List<MovieItemDB>> = movieDao.getAllMovieFavorite()

    fun getMovieFavorite(): LiveData<List<MovieItemDB>> = movieDao.getMovieFavorite()

    fun insertMovie(movieItemDB: List<MovieItemDB>) = movieDao.insertMovie(movieItemDB)

}