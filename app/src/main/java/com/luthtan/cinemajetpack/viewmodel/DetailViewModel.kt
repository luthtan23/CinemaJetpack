package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.repository.detail.DetailRepository
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel(), KoinComponent {

    private val _detailResponse = MutableLiveData<Resource<DetailResponse>>()
    val detailResponse: LiveData<Resource<DetailResponse>> get() = _detailResponse

    private val _creditResponse = MutableLiveData<Resource<CreditResponse>>()
    val creditResponse: LiveData<Resource<CreditResponse>> get() = _creditResponse

    private val _recommendationResponse = MutableLiveData<Resource<RecommendationResponse>>()
    val recommendationResponse: LiveData<Resource<RecommendationResponse>> get() = _recommendationResponse

    private val _trailerResponse = MutableLiveData<Resource<TrailerResponse>>()
    val trailerResponse: LiveData<Resource<TrailerResponse>> get() = _trailerResponse

    fun getDetailMovie(id: Int) {
        detailRepository.getDetailMovie(_detailResponse, id)
        detailRepository.getDetailCreditsMovie(_creditResponse, id)
        detailRepository.getDetailRecommendationMovie(_recommendationResponse, id)
    }

    fun getDetailTvShow(id: Int) {
        detailRepository.getDetailTvShow(_detailResponse, id)
        detailRepository.getDetailCreditsTvShow(_creditResponse, id)
        detailRepository.getDetailRecommendationTvShow(_recommendationResponse, id)
    }

    fun getDetailVideoMovie(id: Int) {
        detailRepository.getDetailVideosMovie(_trailerResponse, id)
    }

    fun getDetailVideoTvShow(id: Int) {
        detailRepository.getDetailVideosTvShow(_trailerResponse, id)
    }

    fun setDetailEntity(detailResponse: DetailResponse){
        detailRepository.setDetailEntity(detailResponse)
    }
}