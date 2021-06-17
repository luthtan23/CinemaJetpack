package com.luthtan.cinemajetpack.repository.tvshow

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse

interface TvShowRepositoryListener {

    suspend fun getPopularTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse>

    suspend fun getNowPlayingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse>

    suspend fun getTopRatedTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse>

    suspend fun getUpcomingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse>

}