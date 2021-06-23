package com.luthtan.cinemajetpack.di.module

import com.luthtan.cinemajetpack.repository.PreferencesRepository
import com.luthtan.cinemajetpack.repository.detail.DetailRepository
import com.luthtan.cinemajetpack.repository.favorite.movie.MovieFavoriteRepository
import com.luthtan.cinemajetpack.repository.login.LoginRepository
import com.luthtan.cinemajetpack.repository.movie.MovieRepository
import com.luthtan.cinemajetpack.repository.tvshow.TvShowRepository
import org.koin.dsl.module

val repoModule = module {

    single { MovieRepository(get()) }
    single { DetailRepository(get()) }
    single { TvShowRepository(get()) }
    single { LoginRepository(get()) }
    single { PreferencesRepository(get()) }
    single { MovieFavoriteRepository(get()) }

}