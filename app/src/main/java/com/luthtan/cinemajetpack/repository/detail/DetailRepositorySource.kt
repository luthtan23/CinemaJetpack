package com.luthtan.cinemajetpack.repository.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.local.*
import com.luthtan.cinemajetpack.model.bean.response.detail.*
import com.luthtan.cinemajetpack.vo.Resource

interface DetailRepositorySource {

    fun getAllMovieFavoriteList(): LiveData<List<DetailEntity>>

    fun getDetailWithCast(id: Int): LiveData<Resource<DetailWithCast>>

    fun getDetailMovie(id: Int): LiveData<Resource<DetailEntity>>

    fun insertMovie(detailEntity: DetailEntity)

    fun updateMovieFavorite(detailEntity: DetailEntity, newState: Boolean)

    fun getDetailWithRecommendation(id: Int): LiveData<Resource<DetailWithRecommendation>>

    fun getDetailWithTrailer(id: Int): LiveData<Resource<DetailWithTrailer>>

    fun getDetailTvShow(
        detailResponse: MutableLiveData<Resource<DetailResponse>>,
        id: Int
    ): LiveData<Resource<DetailResponse>>

    fun getDetailCreditsTvShow(
        creditResponse: MutableLiveData<Resource<CreditResponse>>,
        id: Int
    ): LiveData<Resource<CreditResponse>>

    fun getDetailRecommendationTvShow(
        recommendationResponse: MutableLiveData<Resource<RecommendationResponse>>,
        id: Int
    ): LiveData<Resource<RecommendationResponse>>

    fun getDetailVideosTvShow(
        trailerResponse: MutableLiveData<Resource<TrailerResponse>>,
        id: Int
    ): LiveData<Resource<TrailerResponse>>

    fun getAllMovieFavorite(): LiveData<List<DetailEntity>>

    fun deleteMovieFavorite(id: Int)
}