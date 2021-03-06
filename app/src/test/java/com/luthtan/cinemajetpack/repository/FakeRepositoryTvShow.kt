package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepositorySource
import com.luthtan.cinemajetpack.vo.Resource

class FakeRepositoryTvShow : TvShowRepositorySource {

    override fun getPopularTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return tvShowResponse
    }

    override fun getNowPlayingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return tvShowResponse
    }

    override fun getTopRatedTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return tvShowResponse
    }

    override fun getUpcomingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return tvShowResponse
    }


}