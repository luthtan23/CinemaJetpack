package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.*
import com.luthtan.cinemajetpack.repository.favorite.movie.MovieFavoriteRepository
import com.luthtan.cinemajetpack.vo.Resource

class MovieFavoriteViewModel(private val movieFavoriteRepository: MovieFavoriteRepository) : ViewModel() {

    private val _detailResponse = MutableLiveData<Resource<DetailResponse>>()
    val detailResponse: LiveData<Resource<DetailResponse>> get() = _detailResponse

    private val _creditResponse = MutableLiveData<Resource<CreditResponse>>()
    val creditResponse: LiveData<Resource<CreditResponse>> get() = _creditResponse

    private val _recommendationResponse = MutableLiveData<Resource<RecommendationResponse>>()
    val recommendationResponse: LiveData<Resource<RecommendationResponse>> get() = _recommendationResponse

    private val _trailerResponse = MutableLiveData<Resource<TrailerResponse>>()
    val trailerResponse: LiveData<Resource<TrailerResponse>> get() = _trailerResponse

    fun setMovieFavorite(detailResponse: DetailResponse) = movieFavoriteRepository.setMovieFavorite(detailResponse)

    fun getMovieFavorite(): LiveData<List<MovieEntity>> = movieFavoriteRepository.getMovieFavorite()

    fun retrieveMovieFavorite(id: Int){
        _detailResponse.postValue(movieFavoriteRepository.retrieveMovieFavorite(id).value)
    }

    fun deleteMovieFavorite(id: Int) = movieFavoriteRepository.deleteMovieFavorite(id)

}