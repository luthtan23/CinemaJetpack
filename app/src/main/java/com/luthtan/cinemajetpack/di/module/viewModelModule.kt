package com.luthtan.cinemajetpack.di.module

import com.luthtan.cinemajetpack.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MovieFavoriteViewModel(get()) }

}