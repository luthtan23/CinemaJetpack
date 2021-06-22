package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepository
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel(), KoinComponent {

    private val _tvShowPopularResponse = MutableLiveData<Resource<TvShowResponse>>()
    val tvShowPopularResponse: LiveData<Resource<TvShowResponse>> get() = _tvShowPopularResponse

    private val _tvShowTopRatedResponse = MutableLiveData<Resource<TvShowResponse>>()
    val tvShowTopRatedResponse: LiveData<Resource<TvShowResponse>> get() = _tvShowTopRatedResponse

    private val _tvShowNowPlayingResponse = MutableLiveData<Resource<TvShowResponse>>()
    val tvShowNowPlayingResponse: LiveData<Resource<TvShowResponse>> get() = _tvShowNowPlayingResponse

    private val _tvShowUpcomingResponse = MutableLiveData<Resource<TvShowResponse>>()
    val tvShowUpcomingResponse: LiveData<Resource<TvShowResponse>> get() = _tvShowUpcomingResponse

    fun getTvShowResponse() {
        tvShowRepository.getPopularTvShow(_tvShowPopularResponse)
        tvShowRepository.getNowPlayingTvShow(_tvShowNowPlayingResponse)
        tvShowRepository.getTopRatedTvShow(_tvShowTopRatedResponse)
        tvShowRepository.getUpcomingTvShow(_tvShowUpcomingResponse)
    }


}