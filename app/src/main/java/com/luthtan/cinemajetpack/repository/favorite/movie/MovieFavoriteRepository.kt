package com.luthtan.cinemajetpack.repository.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResultsItem
import com.luthtan.cinemajetpack.model.remote.LocalDataSource
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class MovieFavoriteRepository(private val localDataSource: LocalDataSource): KoinComponent, MovieFavoriteRepositoryListener{

    override fun setMovieFavorite(detailResponse: DetailResponse) = localDataSource.addMovieFavorite(detailResponse)

    override fun getMovieFavorite(): LiveData<List<MovieEntity>> = localDataSource.getMovieFavorite()

    override fun retrieveMovieFavorite(id: Int): MutableLiveData<Resource<DetailResponse>> = localDataSource.retrieveMovieFavorite(id)

    override fun deleteMovieFavorite(id: Int) = localDataSource.deleteMovieFavorite(id)



}