package com.luthtan.cinemajetpack.di.module

import androidx.room.Room
import com.luthtan.cinemajetpack.MyApplication
import com.luthtan.cinemajetpack.model.local.db.MovieDB
import com.luthtan.cinemajetpack.model.local.db.TvShowDB
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

    single {
        Room.databaseBuilder(
            (androidApplication() as MyApplication),
            TvShowDB::class.java,
            "tvshow_db"
        )
            .build()
    }
    single { (get() as MovieDB).movieDao() }
    single { (get() as TvShowDB).tvShowDao() }


}