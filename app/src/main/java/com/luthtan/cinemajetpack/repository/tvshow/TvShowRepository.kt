package com.luthtan.cinemajetpack.repository.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class TvShowRepository(private val remoteDataResource: RemoteDataSource) : KoinComponent,
    TvShowRepositorySource {

    override fun getPopularTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return remoteDataResource.getPopularTvShow(tvShowResponse)
    }

    override fun getNowPlayingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return remoteDataResource.getNowPlayingTvShow(tvShowResponse)
    }

    override fun getTopRatedTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return remoteDataResource.getTopRatedTvShow(tvShowResponse)
    }

    override fun getUpcomingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): LiveData<Resource<TvShowResponse>> {
        return remoteDataResource.getUpcomingTvShow(tvShowResponse)
    }

}