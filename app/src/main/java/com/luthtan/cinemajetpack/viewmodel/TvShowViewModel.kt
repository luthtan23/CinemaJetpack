package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.repository.TvShowRepository
import org.koin.core.KoinComponent

class TvShowViewModel (private val tvShowRepository: TvShowRepository) : ViewModel(), KoinComponent {

    private val _tvShowPopularResponse = MutableLiveData<TvShowResponse>()
    val tvShowPopularResponse : LiveData<TvShowResponse> get() = _tvShowPopularResponse

    private val _tvShowTopRatedResponse = MutableLiveData<TvShowResponse>()
    val tvShowTopRatedResponse : LiveData<TvShowResponse> get() = _tvShowTopRatedResponse

    private val _tvShowNowPlayingResponse = MutableLiveData<TvShowResponse>()
    val tvShowNowPlayingResponse : LiveData<TvShowResponse> get() = _tvShowNowPlayingResponse

    private val _tvShowUpcomingResponse = MutableLiveData<TvShowResponse>()
    val tvShowUpcomingResponse : LiveData<TvShowResponse> get() = _tvShowUpcomingResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    fun getTvShowResponse() {
        tvShowRepository.getPopularTvShow(_tvShowPopularResponse, _errorResponse)
        tvShowRepository.getNowPlayingTvShow(_tvShowNowPlayingResponse, _errorResponse)
        tvShowRepository.getTopRatedTvShow(_tvShowTopRatedResponse, _errorResponse)
        tvShowRepository.getUpcomingTvShow(_tvShowUpcomingResponse, _errorResponse)
    }


}