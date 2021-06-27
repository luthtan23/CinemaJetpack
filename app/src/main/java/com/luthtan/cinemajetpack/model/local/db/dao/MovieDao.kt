package com.luthtan.cinemajetpack.model.local.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.luthtan.cinemajetpack.model.bean.local.*

@Dao
interface MovieDao {

    @Query("SELECT * from movie_db")
    fun getAllMovieFavorite(): LiveData<List<DetailEntity>>

    @Query("SELECT * FROM movie_db where isMovieFavorite = 1")
    fun getAllMovieFavoriteList(): DataSource.Factory<Int, DetailEntity>

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

    @Transaction
    @Query("SELECT * FROM movie_db WHERE detailId = :detailId")
    fun getDetailWithRecommendation(detailId: Int): LiveData<DetailWithRecommendation>

    @Transaction
    @Query("SELECT * FROM movie_db WHERE detailId = :detailId")
    fun getDetailWithTrailer(detailId: Int): LiveData<DetailWithTrailer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGetDetailWithCastList(castItem: List<CastItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGetDetailWithRecommendationList(recommendationEntity: List<RecommendationItemsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGetDetailWithTrailer(trailerItemsEntity: List<TrailerItemsEntity>)


}