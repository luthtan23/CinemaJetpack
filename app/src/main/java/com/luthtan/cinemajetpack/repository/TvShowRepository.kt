package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.BuildConfig
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.model.remote.ApiService
import com.luthtan.cinemajetpack.model.remote.DataFetchCall
import org.koin.core.KoinComponent
import retrofit2.Response

class TvShowRepository (private val apiService: ApiService) : KoinComponent {

    fun getPopularTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowPopular(BuildConfig.TOKEN)
            }
        }.execute()
    }


    fun getTopRatedTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowTopRated(BuildConfig.TOKEN)
            }
        }.execute()
    }

    fun getUpcomingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowUpComing(BuildConfig.TOKEN)
            }
        }.execute()
    }

    fun getNowPlayingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowNowPlaying(BuildConfig.TOKEN)
            }
        }.execute()
    }

    fun getSimilarTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowSimilar(BuildConfig.TOKEN, id)
            }
        }.execute()
    }
    
}