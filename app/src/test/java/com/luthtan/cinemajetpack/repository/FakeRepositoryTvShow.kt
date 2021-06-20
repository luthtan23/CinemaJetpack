package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepositoryListener

class FakeRepositoryTvShow : TvShowRepositoryListener {

    override suspend fun getPopularTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        return tvShowResponse
    }

    override suspend fun getNowPlayingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        return tvShowResponse
    }

    override suspend fun getTopRatedTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        return tvShowResponse
    }

    override suspend fun getUpcomingTvShow(
        tvShowResponse: MutableLiveData<TvShowResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<TvShowResponse> {
        return tvShowResponse
    }
}