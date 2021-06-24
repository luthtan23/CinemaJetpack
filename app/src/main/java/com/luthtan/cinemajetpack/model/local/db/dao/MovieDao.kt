package com.luthtan.cinemajetpack.model.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity

@Dao
interface MovieDao {

    @Query("SELECT * from movie_db")
    fun getAllMovieFavorite(): List<DetailEntity>

    @Query("SELECT * FROM movie_db WHERE id = :id")
    fun retrieveMovieFavorite(id: Int): DetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(DetailEntity: DetailEntity)

}