package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.BuildConfig
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.model.remote.ApiService
import com.luthtan.cinemajetpack.model.remote.DataFetchCall
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepositoryListener
import org.koin.core.KoinComponent
import retrofit2.Response

class TvShowRepository(private val apiService: ApiService) : KoinComponent,
    TvShowRepositoryListener {

    override suspend fun getPopularTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowPopular(BuildConfig.TOKEN)
            }
        }.execute()
        return tvShowResponse
    }

    override suspend fun getNowPlayingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowNowPlaying(BuildConfig.TOKEN)
            }
        }.execute()
        return tvShowResponse
    }

    override suspend fun getTopRatedTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowTopRated(BuildConfig.TOKEN)
            }
        }.execute()
        return tvShowResponse
    }

    override suspend fun getUpcomingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        object : DataFetchCall<TvShowResponse>(tvShowResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowUpComing(BuildConfig.TOKEN)
            }
        }.execute()
        return tvShowResponse
    }

}