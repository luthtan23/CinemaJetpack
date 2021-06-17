package com.luthtan.cinemajetpack.di.module

import com.luthtan.cinemajetpack.repository.*
import org.koin.dsl.module

val repoModule = module {

    single { MovieRepository(get()) }
    single { DetailRepository(get()) }
    single { TvShowRepository(get()) }
    single { LoginRepository(get()) }
    single { PreferencesRepository(get()) }

}