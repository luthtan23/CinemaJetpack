package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.BuildConfig
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.model.remote.ApiService
import com.luthtan.cinemajetpack.model.remote.DataFetchCall
import org.koin.core.KoinComponent
import retrofit2.Response

class DetailRepository (private val apiService: ApiService) : KoinComponent {

    fun getDetailMovie(
        detailResponse: MutableLiveData<DetailResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<DetailResponse>(detailResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<DetailResponse> {
                return apiService.getMovieDetail(BuildConfig.TOKEN, id)
            }
        }.execute()
    }

    fun getDetailCreditsMovie(
        creditResponse: MutableLiveData<CreditResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<CreditResponse>(creditResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<CreditResponse> {
                return apiService.getMovieDetailCredits(BuildConfig.TOKEN, id)
            }
        }.execute()
    }

    fun getDetailRecommendationMovie(
        recommendationResponse: MutableLiveData<RecommendationResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<RecommendationResponse>(recommendationResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<RecommendationResponse> {
                return apiService.getMovieDetailRecommendation(BuildConfig.TOKEN, id)
            }
        }.execute()
    }

    fun getDetailVideosMovie(
        trailerResponse: MutableLiveData<TrailerResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<TrailerResponse>(trailerResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TrailerResponse> {
                return apiService.getMovieDetailVideos(BuildConfig.TOKEN, id)
            }
        }.execute()
    }

    fun getDetailTvShow(
        detailResponse: MutableLiveData<DetailResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<DetailResponse>(detailResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<DetailResponse> {
                return apiService.getTvShowDetail(BuildConfig.TOKEN, id)
            }
        }.execute()
    }

    fun getDetailCreditsTvShow(
        creditResponse: MutableLiveData<CreditResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<CreditResponse>(creditResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<CreditResponse> {
                return apiService.getTvShowDetailCredits(BuildConfig.TOKEN, id)
            }
        }.execute()
    }

    fun getDetailRecommendationTvShow(
        recommendationResponse: MutableLiveData<RecommendationResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<RecommendationResponse>(recommendationResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<RecommendationResponse> {
                return apiService.getTvShowDetailRecommendation(BuildConfig.TOKEN, id)
            }
        }.execute()
    }

    fun getDetailVideosTvShow(
        trailerResponse: MutableLiveData<TrailerResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<TrailerResponse>(trailerResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TrailerResponse> {
                return apiService.getTvShowDetailVideos(BuildConfig.TOKEN, id)
            }
        }.execute()
    }
}