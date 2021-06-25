package com.luthtan.cinemajetpack.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.local.*
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.repository.detail.DetailRepository
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel(), KoinComponent {

    private val extraId = MutableLiveData<Int>()

    fun setExtraId(id: Int) {
        this.extraId.value = id
    }

    val detailMovieFavorite: LiveData<Resource<DetailEntity>> = Transformations.switchMap(extraId) { id ->
        detailRepository.getDetailMovie(id)
    }

    val detailWithCast: LiveData<Resource<DetailWithCast>> = Transformations.switchMap(extraId) { id ->
        detailRepository.getDetailWithCast(id)
    }

    val detailWithRecommendation: LiveData<Resource<DetailWithRecommendation>> = Transformations.switchMap(extraId) { id ->
        detailRepository.getDetailWithRecommendation(id)
    }

    val detailWithTrailer: LiveData<Resource<DetailWithTrailer>> = Transformations.switchMap(extraId) { id ->
        detailRepository.getDetailWithTrailer(id)
    }

    fun insertMovie(detailEntity: DetailEntity) = detailRepository.insertMovie(detailEntity)

    fun setMovieFavorite() {
        val detailMovieFavorite = detailMovieFavorite.value
        if (detailMovieFavorite != null) {
            val detailMovieFavoriteData = detailMovieFavorite.data
            if (detailMovieFavoriteData != null) {
                val isMovieFavorite = !detailMovieFavoriteData.isMovieFavorite
                detailRepository.updateMovieFavorite(detailMovieFavoriteData, isMovieFavorite)
            }
        }
    }

    private val _recommendationResponse = MutableLiveData<Resource<RecommendationResponse>>()
    val recommendationResponse: LiveData<Resource<RecommendationResponse>> get() = _recommendationResponse

    private val _trailerResponse = MutableLiveData<Resource<TrailerResponse>>()
    val trailerResponse: LiveData<Resource<TrailerResponse>> get() = _trailerResponse

    fun getAllMovieFavorite(): LiveData<List<DetailEntity>> = detailRepository.getAllMovieFavorite()

    fun getDetailTvShow(id: Int) {
        detailRepository.getDetailRecommendationTvShow(_recommendationResponse, id)
    }

    fun getDetailVideoTvShow(id: Int) = detailRepository.getDetailVideosTvShow(_trailerResponse, id)


}