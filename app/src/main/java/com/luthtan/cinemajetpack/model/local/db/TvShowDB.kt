package com.luthtan.cinemajetpack.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.luthtan.cinemajetpack.model.bean.local.*
import com.luthtan.cinemajetpack.model.local.db.dao.TvShowDao

@Database(
    entities = [DetailEntity::class, CastItemEntity::class, RecommendationItemsEntity::class, TrailerItemsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenresTypeConverter::class)
abstract class TvShowDB : RoomDatabase() {

    abstract fun tvShowDao(): TvShowDao
}