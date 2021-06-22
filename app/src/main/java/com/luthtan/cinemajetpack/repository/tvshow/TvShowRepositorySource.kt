package com.luthtan.cinemajetpack.repository.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.vo.Resource

interface TvShowRepositorySource {

    fun getPopularTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>>

    fun getNowPlayingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>>

    fun getTopRatedTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>>

    fun getUpcomingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>>

}