package com.luthtan.cinemajetpack.di.module

import com.luthtan.cinemajetpack.model.remote.LocalDataSource
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.util.AppExecutors
import org.koin.dsl.module

val remoteModule = module {

    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single { AppExecutors() }

}