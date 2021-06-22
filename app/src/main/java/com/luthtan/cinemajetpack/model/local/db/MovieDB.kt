package com.luthtan.cinemajetpack.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luthtan.cinemajetpack.model.bean.local.MovieItemDB
import com.luthtan.cinemajetpack.model.local.db.dao.MovieDao

@Database(entities = [MovieItemDB::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}