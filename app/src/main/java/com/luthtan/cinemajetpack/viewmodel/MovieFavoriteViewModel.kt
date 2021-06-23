package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.repository.favorite.movie.MovieFavoriteRepository

class MovieFavoriteViewModel(private val movieFavoriteRepository: MovieFavoriteRepository) : ViewModel() {

    fun setMovieFavorite(detailResponse: DetailResponse) = movieFavoriteRepository.setMovieFavorite(detailResponse)

    fun getMovieFavorite(): LiveData<List<MovieEntity>> = movieFavoriteRepository.getMovieFavorite()

    fun retrieveMovieFavorite(id: Int): LiveData<MovieEntity> = movieFavoriteRepository.retrieveMovieFavorite(id)

    fun deleteMovieFavorite(id: Int) = movieFavoriteRepository.deleteMovieFavorite(id)

    fun getMovieFavoriteCastItem(): LiveData<List<CastItem>> = movieFavoriteRepository.getMovieFavoriteCastItem()
}