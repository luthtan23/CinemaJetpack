package com.luthtan.cinemajetpack.model.local.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luthtan.cinemajetpack.model.bean.local.MovieEntity
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem

@Dao
interface MovieDao {

    @Query("SELECT * from movie_db")
    fun getAllMovieFavorite(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_db WHERE id = :id")
    fun retrieveMovieFavorite(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM movie_db WHERE id = :id")
    fun deleteMovieFavorite(id: Int)


}