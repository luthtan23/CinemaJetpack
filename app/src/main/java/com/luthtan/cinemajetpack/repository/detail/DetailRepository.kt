package com.luthtan.cinemajetpack.repository.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class DetailRepository(private val remoteDataSource: RemoteDataSource) : KoinComponent,
    DetailRepositorySource {

    override fun getDetailMovie(
        detailResponse: MutableLiveData<Resource<DetailResponse>>,
        id: Int
    ): LiveData<Resource<DetailResponse>> {
        return remoteDataSource.getDetailMovie(detailResponse, id)
    }

    override fun getDetailCreditsMovie(
        creditResponse: MutableLiveData<Resource<CreditResponse>>,
        id: Int
    ): LiveData<Resource<CreditResponse>> {
        return remoteDataSource.getDetailCreditsMovie(creditResponse, id)
    }

    override fun getDetailRecommendationMovie(
        recommendationResponse: MutableLiveData<Resource<RecommendationResponse>>,
        id: Int
    ): LiveData<Resource<RecommendationResponse>> {
        return remoteDataSource.getDetailRecommendationMovie(recommendationResponse, id)
    }

    override fun getDetailVideosMovie(
        trailerResponse: MutableLiveData<Resource<TrailerResponse>>,
        id: Int
    ): LiveData<Resource<TrailerResponse>> {
        return remoteDataSource.getDetailVideosMovie(trailerResponse, id)
    }

    override fun getDetailTvShow(
        detailResponse: MutableLiveData<Resource<DetailResponse>>,
        id: Int
    ): LiveData<Resource<DetailResponse>> {
        return remoteDataSource.getDetailTvShow(detailResponse, id)
    }

    override fun getDetailCreditsTvShow(
        creditResponse: MutableLiveData<Resource<CreditResponse>>,
        id: Int
    ): LiveData<Resource<CreditResponse>> {
        return remoteDataSource.getDetailCreditsTvShow(creditResponse, id)
    }

    override fun getDetailRecommendationTvShow(
        recommendationResponse: MutableLiveData<Resource<RecommendationResponse>>,
        id: Int
    ): LiveData<Resource<RecommendationResponse>> {
        return remoteDataSource.getDetailRecommendationTvShow(recommendationResponse, id)
    }

    override fun getDetailVideosTvShow(
        trailerResponse: MutableLiveData<Resource<TrailerResponse>>,
        id: Int
    ): LiveData<Resource<TrailerResponse>> {
        return remoteDataSource.getDetailVideosTvShow(trailerResponse, id)
    }


}