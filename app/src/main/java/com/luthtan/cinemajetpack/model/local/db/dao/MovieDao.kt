package com.luthtan.cinemajetpack.model.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * from movie_db")
    fun getAllMovieFavorite(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_db where isMovieFavorite = 1")
    fun getMovieFavorite(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_db WHERE id = :id")
    fun retrieveMovieFavorite(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity)

}