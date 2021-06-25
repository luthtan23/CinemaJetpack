package com.luthtan.cinemajetpack.model.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.luthtan.cinemajetpack.model.bean.local.*

@Dao
interface TvShowDao {

    @Query("SELECT * from movie_db")
    fun getAllTvShowFavorite(): LiveData<List<DetailEntity>>

    @Query("SELECT * FROM movie_db where isTvShowFavorite = 1")
    fun getAllTvShowFavoriteList(): LiveData<List<DetailEntity>>

    @Query("SELECT * FROM movie_db WHERE detailId = :id")
    fun retrieveTvShowFavorite(id: Int): LiveData<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(detailEntity: DetailEntity)

    @Query("DELETE FROM movie_db WHERE detailId = :id")
    fun deleteTvShowFavorite(id: Int)

    @Update
    fun updateTvShowFavorite(detailEntity: DetailEntity)

    @Transaction
    @Query("SELECT * FROM movie_db WHERE detailId = :detailId")
    fun getDetailWithCast(detailId: Int): LiveData<DetailWithCast>

    @Transaction
    @Query("SELECT * FROM movie_db WHERE detailId = :detailId")
    fun getDetailWithRecommendation(detailId: Int): LiveData<DetailWithRecommendation>

    @Transaction
    @Query("SELECT * FROM movie_db WHERE detailId = :detailId")
    fun getDetailWithTrailer(detailId: Int): LiveData<DetailWithTrailer>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGetDetailWithCastList(castItem: List<CastItemEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGetDetailWithRecommendationList(recommendationEntity: List<RecommendationItemsEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGetDetailWithTrailer(trailerItemsEntity: List<TrailerItemsEntity>)

}