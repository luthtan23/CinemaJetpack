package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.local.CastItemEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.repository.detail.DetailRepository
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel(), KoinComponent {

    private val _creditResponse = MutableLiveData<Resource<CreditResponse>>()
    val creditResponse: LiveData<Resource<CreditResponse>> get() = _creditResponse

    private val _recommendationResponse = MutableLiveData<Resource<RecommendationResponse>>()
    val recommendationResponse: LiveData<Resource<RecommendationResponse>> get() = _recommendationResponse

    private val _trailerResponse = MutableLiveData<Resource<TrailerResponse>>()
    val trailerResponse: LiveData<Resource<TrailerResponse>> get() = _trailerResponse

    fun getDetailMovie(id: Int) {
        detailRepository.getDetailRecommendationMovie(_recommendationResponse, id)
    }

    fun getDetailMovieFavorite(id: Int): LiveData<Resource<DetailEntity>> = detailRepository.getDetailMovie(id)

    fun getAllMovieFavorite(): LiveData<List<DetailEntity>> = detailRepository.getAllMovieFavorite()

    fun getDetailTvShow(id: Int) {
        detailRepository.getDetailCreditsTvShow(_creditResponse, id)
        detailRepository.getDetailRecommendationTvShow(_recommendationResponse, id)
    }

    fun getDetailVideoMovie(id: Int) = detailRepository.getDetailVideosMovie(_trailerResponse, id)

    fun getDetailVideoTvShow(id: Int) = detailRepository.getDetailVideosTvShow(_trailerResponse, id)

    val detailId = MutableLiveData<Int>()

    fun getMovieFavoriteCastList(id: Int): LiveData<Resource<DetailWithCast>> = Transformations.switchMap(detailId) { mCourseId ->
        detailRepository.getCourseWithModules(id)
    }

    fun insertMovie(detailEntity: DetailEntity) = detailRepository.insertMovie(detailEntity)

    fun updateMovieFavorite(detailEntity: DetailEntity, newState: Boolean) {
        detailRepository.updateMovieFavorite(detailEntity, newState)
    }


}