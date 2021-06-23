package com.luthtan.cinemajetpack.repository.favorite.movie

import androidx.lifecycle.LiveData
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResultsItem
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import org.koin.core.KoinComponent

class MovieFavoriteRepository(private val remoteDataSource: RemoteDataSource): KoinComponent, MovieFavoriteRepositoryListener{

    override fun setMovieFavorite(detailResponse: DetailResponse) {
        remoteDataSource.addMovieFavorite(detailResponse)
    }

    override fun getMovieFavorite(): LiveData<List<MovieEntity>> = remoteDataSource.getMovieFavorite()

    override fun retrieveMovieFavorite(id: Int): LiveData<MovieEntity> = remoteDataSource.retrieveMovieFavorite(id)

}