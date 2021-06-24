package com.luthtan.cinemajetpack.repository.favorite.movie

import com.luthtan.cinemajetpack.model.remote.LocalDataSource
import org.koin.core.KoinComponent

class MovieFavoriteRepository(private val localDataSource: LocalDataSource): KoinComponent, MovieFavoriteRepositoryListener{




}