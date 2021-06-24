package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.local.db.dao.MovieDao
import com.luthtan.cinemajetpack.util.AppExecutors
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import com.luthtan.cinemajetpack.vo.Resource
import com.luthtan.cinemajetpack.vo.Status
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(private val movieDao: MovieDao, private val mExecutors: AppExecutors) {

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

    fun retrieveMovieFavorite(id: Int): MutableLiveData<Resource<DetailResponse>> {
        val detailResponse = MutableLiveData<Resource<DetailResponse>>()
        GlobalScope.launch {
            try {
                val json = Gson().toJson(movieDao.retrieveMovieFavorite(id))
                val gson = Gson().fromJson(json, DetailResponse::class.java)
                detailResponse.postValue(Resource.success(gson))
            } catch (e: Exception) {
                e.printStackTrace()
                detailResponse.postValue(Resource.error("Error DB", null))
            }
        }
        return detailResponse
    }

    fun deleteMovieFavorite(id: Int) {
        EspressIdlingResources.increment()
        GlobalScope.launch {
            movieDao.deleteMovieFavorite(id)
            EspressIdlingResources.decrement()
        }
    }


}