package com.luthtan.cinemajetpack.repository.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResultsItem
import com.luthtan.cinemajetpack.vo.Resource

interface MovieFavoriteRepositoryListener {

    fun setMovieFavorite(detailResponse: DetailResponse)

    fun getMovieFavorite(): LiveData<List<MovieEntity>>

    fun retrieveMovieFavorite(id: Int): MutableLiveData<Resource<DetailResponse>>

    fun deleteMovieFavorite(id: Int)


}