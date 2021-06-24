package com.luthtan.cinemajetpack.model.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.luthtan.cinemajetpack.model.bean.local.CastItemEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.response.detail.CastItem

@Dao
interface MovieDao {

    @Query("SELECT * from movie_db")
    fun getAllMovieFavorite(): LiveData<List<DetailEntity>>

    @Query("SELECT * FROM movie_db WHERE detailId = :id")
    fun retrieveMovieFavorite(id: Int): LiveData<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(detailEntity: DetailEntity)

    @Query("DELETE FROM movie_db WHERE detailId = :id")
    fun deleteMovieFavorite(id: Int)

    @Update
    fun updateMovieFavorite(detailEntity: DetailEntity)

    @Transaction
    @Query("SELECT * FROM movie_db WHERE detailId = :detailId")
    fun getDetailWithCast(detailId: Int): LiveData<DetailWithCast>

    @Query("SELECT * FROM castEntity WHERE detailId = :detailId")
    fun getDetailWithCastList(detailId: Int): LiveData<List<CastItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCastItemList(castItem: List<CastItemEntity>)


}