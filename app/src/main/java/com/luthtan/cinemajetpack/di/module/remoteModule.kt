package com.luthtan.cinemajetpack.di.module

import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import org.koin.dsl.module

val remoteModule = module {

    single { RemoteDataSource(get(), get()) }

}