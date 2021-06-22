package com.luthtan.cinemajetpack.model.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luthtan.cinemajetpack.model.bean.local.MovieItemDB

@Dao
interface MovieDao {

    @Query("SELECT * from movie_db")
    fun getAllMovieFavorite(): LiveData<List<MovieItemDB>>

    @Query("SELECT * FROM movie_db where isMovieFavorite = 1")
    fun getMovieFavorite(): LiveData<List<MovieItemDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieItemDB: List<MovieItemDB>)

}