package com.luthtan.cinemajetpack.repository.detail

import androidx.lifecycle.LiveData
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.local.DetailWithRecommendation
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
import com.luthtan.cinemajetpack.vo.Resource

interface DetailRepositorySource {

    fun getAllMovieFavoriteList(): LiveData<List<DetailEntity>>

    fun getDetailWithCast(id: Int): LiveData<Resource<DetailWithCast>>

    fun getDetailMovie(id: Int): LiveData<Resource<DetailEntity>>

    fun insertMovie(detailEntity: DetailEntity)

    fun updateMovieFavorite(detailEntity: DetailEntity, newState: Boolean)

    fun getDetailWithRecommendation(id: Int): LiveData<Resource<DetailWithRecommendation>>

    fun getDetailWithTrailer(id: Int): LiveData<Resource<DetailWithTrailer>>

    fun getAllMovieFavorite(): LiveData<List<DetailEntity>>

    fun deleteMovieFavorite(id: Int)

    fun getAllTvShowFavoriteList(): LiveData<List<DetailEntity>>

    fun getTvShowDetailWithCast(id: Int): LiveData<Resource<DetailWithCast>>

    fun getDetailTvShow(id: Int): LiveData<Resource<DetailEntity>>

    fun insertTvShow(detailEntity: DetailEntity)

    fun updateTvShowFavorite(detailEntity: DetailEntity, newState: Boolean)

    fun getTvShowDetailWithRecommendation(id: Int): LiveData<Resource<DetailWithRecommendation>>

    fun getTvShowDetailWithTrailer(id: Int): LiveData<Resource<DetailWithTrailer>>

    fun getAllTvShowFavorite(): LiveData<List<DetailEntity>>

    fun deleteTvShowFavorite(id: Int)

}