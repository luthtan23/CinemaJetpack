package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.local.DetailWithRecommendation
import com.luthtan.cinemajetpack.model.bean.local.DetailWithTrailer
import com.luthtan.cinemajetpack.repository.detail.DetailRepository
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel(), KoinComponent {

    private val extraId = MutableLiveData<Int>()

    fun setExtraId(id: Int) {
        this.extraId.value = id
    }

    val detailMovieFavorite: LiveData<Resource<DetailEntity>> =
        Transformations.switchMap(extraId) { id ->
            detailRepository.getDetailMovie(id)
        }

    val detailWithCast: LiveData<Resource<DetailWithCast>> =
        Transformations.switchMap(extraId) { id ->
            detailRepository.getDetailWithCast(id)
        }

    val detailWithRecommendation: LiveData<Resource<DetailWithRecommendation>> =
        Transformations.switchMap(extraId) { id ->
            detailRepository.getDetailWithRecommendation(id)
        }

    val detailWithTrailer: LiveData<Resource<DetailWithTrailer>> =
        Transformations.switchMap(extraId) { id ->
            detailRepository.getDetailWithTrailer(id)
        }

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

    fun deleteMovieFavorite(detailEntity: DetailEntity) {
        val isMovieFavorite = !detailEntity.isMovieFavorite
        detailRepository.updateMovieFavorite(detailEntity, isMovieFavorite)
    }

    fun getAllMovieFavorite(): LiveData<List<DetailEntity>> =
        detailRepository.getAllMovieFavoriteList()

    val detailTvShowFavorite: LiveData<Resource<DetailEntity>> =
        Transformations.switchMap(extraId) { id ->
            detailRepository.getDetailTvShow(id)
        }

    val detailWithCastTvShow: LiveData<Resource<DetailWithCast>> =
        Transformations.switchMap(extraId) { id ->
            detailRepository.getTvShowDetailWithCast(id)
        }

    val detailWithRecommendationTvShow: LiveData<Resource<DetailWithRecommendation>> =
        Transformations.switchMap(extraId) { id ->
            detailRepository.getTvShowDetailWithRecommendation(id)
        }

    val detailWithTrailerTvShow: LiveData<Resource<DetailWithTrailer>> =
        Transformations.switchMap(extraId) { id ->
            detailRepository.getTvShowDetailWithTrailer(id)
        }

    fun setTvShowFavorite() {
        val detailTvShowFavorite = detailTvShowFavorite.value
        if (detailTvShowFavorite != null) {
            val detailTvShowFavoriteData = detailTvShowFavorite.data
            if (detailTvShowFavoriteData != null) {
                val isTvShowFavorite = !detailTvShowFavoriteData.isTvShowFavorite
                detailRepository.updateTvShowFavorite(detailTvShowFavoriteData, isTvShowFavorite)
            }
        }
    }

    fun deleteTvShowFavorite(detailEntity: DetailEntity) {
        val isMovieFavorite = !detailEntity.isMovieFavorite
        detailRepository.updateTvShowFavorite(detailEntity, isMovieFavorite)
    }

    fun getAllTvShowFavorite(): LiveData<List<DetailEntity>> =
        detailRepository.getAllTvShowFavoriteList()


}