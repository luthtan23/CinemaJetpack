package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.local.db.dao.MovieDao
import com.luthtan.cinemajetpack.vo.Resource

class LocalDataSource(private val movieDao: MovieDao) {

    fun insertDetailMovie(detailResponse: DetailResponse){
        try {
            val gson = Gson().toJson(detailResponse)
            val detailEntity = Gson().fromJson(gson, DetailEntity::class.java)
            movieDao.insertMovie(detailEntity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getDetailMovie(
        detailResponse: MutableLiveData<Resource<DetailEntity>>,
        id: Int
    ): MutableLiveData<Resource<DetailEntity>> {
        detailResponse.postValue(Resource.success(movieDao.retrieveMovieFavorite(id)))
        return detailResponse
    }

}