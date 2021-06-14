package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.repository.DetailRepository
import org.koin.core.KoinComponent

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel(), KoinComponent {

    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse: LiveData<DetailResponse> get() = _detailResponse

    private val _creditResponse = MutableLiveData<CreditResponse>()
    val creditResponse: LiveData<CreditResponse> get() = _creditResponse

    private val _recommendationResponse = MutableLiveData<RecommendationResponse>()
    val recommendationResponse: LiveData<RecommendationResponse> get() = _recommendationResponse

    private val _trailerResponse = MutableLiveData<TrailerResponse>()
    val trailerResponse: LiveData<TrailerResponse> get() = _trailerResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    fun getDetailMovie(id: Int) {
        detailRepository.getDetailMovie(_detailResponse, _errorResponse, id)
        detailRepository.getDetailCreditsMovie(_creditResponse, _errorResponse, id)
        detailRepository.getDetailRecommendationMovie(_recommendationResponse, _errorResponse, id)
    }

    fun getDetailTvShow(id: Int) {
        detailRepository.getDetailTvShow(_detailResponse, _errorResponse, id)
        detailRepository.getDetailCreditsTvShow(_creditResponse, _errorResponse, id)
        detailRepository.getDetailRecommendationTvShow(_recommendationResponse, _errorResponse, id)
    }

    fun getDetailVideoMovie(id: Int) {
        detailRepository.getDetailVideosMovie(_trailerResponse, _errorResponse, id)
    }

    fun getDetailVideoTvShow(id: Int) {
        detailRepository.getDetailVideosTvShow(_trailerResponse, _errorResponse, id)
    }
}