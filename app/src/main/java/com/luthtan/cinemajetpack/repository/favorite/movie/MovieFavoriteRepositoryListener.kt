package com.luthtan.cinemajetpack.repository.favorite.movie

import androidx.lifecycle.LiveData
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResultsItem

interface MovieFavoriteRepositoryListener {

    fun setMovieFavorite(detailResponse: DetailResponse)

    fun getMovieFavorite(): LiveData<List<MovieEntity>>

    fun retrieveMovieFavorite(id: Int): LiveData<MovieEntity>

    fun deleteMovieFavorite(id: Int)

    fun getMovieFavoriteCastItem(): LiveData<List<CastItem>>


}