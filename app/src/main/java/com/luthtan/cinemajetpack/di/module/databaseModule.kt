package com.luthtan.cinemajetpack.di.module

import androidx.room.Room
import com.luthtan.cinemajetpack.MyApplication
import com.luthtan.cinemajetpack.model.local.db.MovieDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

var databaseModule = module {

    single {
        Room.databaseBuilder(
            (androidApplication() as MyApplication),
            MovieDB::class.java,
            "movie_db"
        )
            .build()
    }
    single { (get() as MovieDB).movieDao() }


}