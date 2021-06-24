package com.luthtan.cinemajetpack.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.luthtan.cinemajetpack.model.bean.local.CastItemEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.GenresTypeConverter
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem
import com.luthtan.cinemajetpack.model.local.db.dao.MovieDao

@Database(entities = [DetailEntity::class, CastItemEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenresTypeConverter::class)
abstract class MovieDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}