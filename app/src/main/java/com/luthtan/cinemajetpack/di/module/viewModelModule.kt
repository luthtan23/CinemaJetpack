package com.luthtan.cinemajetpack.di.module

import com.luthtan.cinemajetpack.viewmodel.DetailViewModel
import com.luthtan.cinemajetpack.viewmodel.LoginViewModel
import com.luthtan.cinemajetpack.viewmodel.MovieViewModel
import com.luthtan.cinemajetpack.viewmodel.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { LoginViewModel(get()) }

}