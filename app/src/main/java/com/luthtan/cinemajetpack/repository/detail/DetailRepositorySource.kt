package com.luthtan.cinemajetpack.repository.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.local.CastItemEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.response.detail.*
import com.luthtan.cinemajetpack.vo.Resource

interface DetailRepositorySource {

    fun getCourseWithModules(id: Int): LiveData<Resource<DetailWithCast>>

    fun getDetailMovie(id: Int): LiveData<Resource<DetailEntity>>

    fun insertMovie(detailEntity: DetailEntity)

    fun updateMovieFavorite(detailEntity: DetailEntity, newState: Boolean)

    fun getDetailCreditsMovie(id: Int): LiveData<Resource<List<CastItemEntity>>>

    fun getDetailRecommendationMovie(
        recommendationResponse: MutableLiveData<Resource<RecommendationResponse>>,
        id: Int
    ): LiveData<Resource<RecommendationResponse>>

    fun getDetailVideosMovie(
        trailerResponse: MutableLiveData<Resource<TrailerResponse>>,
        id: Int
    ): LiveData<Resource<TrailerResponse>>

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