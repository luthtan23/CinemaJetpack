package com.luthtan.cinemajetpack.repository.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.NetworkBoundResource
import com.luthtan.cinemajetpack.model.bean.local.*
import com.luthtan.cinemajetpack.model.bean.response.detail.*
import com.luthtan.cinemajetpack.model.remote.ApiResponse
import com.luthtan.cinemajetpack.model.remote.LocalDataSource
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.util.AppExecutors
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent
import java.lang.Exception

class DetailRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : KoinComponent,
    DetailRepositorySource {

    override fun getDetailMovie(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBoundResource<DetailEntity, DetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> = localDataSource.retrieveMovieFavorite(id)

            override fun shouldFetch(data: DetailEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<DetailResponse>> = remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: DetailResponse) {
                val json = Gson().toJson(data)
                val movieEntity = Gson().fromJson(json, DetailEntity::class.java)
                localDataSource.addMovieFavorite(movieEntity)
            }
        }.asLiveData()
    }

    override fun getAllMovieFavoriteList(): LiveData<List<DetailEntity>> = localDataSource.getAllMovieFavoriteList()

    override fun getDetailWithCast(id: Int): LiveData<Resource<DetailWithCast>> {
        return object : NetworkBoundResource<DetailWithCast, List<CastItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithCast> = localDataSource.getDetailWithCast(id)

            override fun shouldFetch(data: DetailWithCast?): Boolean = data?.castItemEntity == null || data.castItemEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CastItem>>> = remoteDataSource.getDetailCreditsMovie(id)

            override fun saveCallResult(data: List<CastItem>) {
                try {
                    val json = Gson().toJson(data)
                    val arrayCast = Gson().fromJson(json, Array<CastItemEntity>::class.java)
                    val castList = ArrayList<CastItemEntity>()
                    castList.addAll(arrayCast)
                    localDataSource.insertDetailWithCastList(castList)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.asLiveData()
    }

    override fun insertMovie(detailEntity: DetailEntity) {
        appExecutors.diskIO().execute{ localDataSource.addMovieFavorite(detailEntity) }
    }

    override fun updateMovieFavorite(detailEntity: DetailEntity, newState: Boolean) {
        appExecutors.diskIO().execute{ localDataSource.updateMovieFavorite(detailEntity, newState) }
    }

    override fun getDetailWithRecommendation(id: Int): LiveData<Resource<DetailWithRecommendation>> {
        return object : NetworkBoundResource<DetailWithRecommendation, List<RecommendationItems>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithRecommendation> = localDataSource.getDetailWithRecommendation(id)

            override fun shouldFetch(data: DetailWithRecommendation?): Boolean = data?.recommendationItemsEntity == null || data.recommendationItemsEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<RecommendationItems>>> = remoteDataSource.getDetailRecommendationMovie(id)

            override fun saveCallResult(data: List<RecommendationItems>) {
                try {
                    val json = Gson().toJson(data)
                    val arrayRecommendation = Gson().fromJson(json, Array<RecommendationItemsEntity>::class.java)
                    val recommendationList = ArrayList<RecommendationItemsEntity>()
                    recommendationList.addAll(arrayRecommendation)
                    localDataSource.insertDetailWithRecommendationList(recommendationList)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.asLiveData()
    }

    override fun getDetailWithTrailer(id: Int): LiveData<Resource<DetailWithTrailer>> {
        return object : NetworkBoundResource<DetailWithTrailer, List<TrailerItems>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithTrailer> = localDataSource.getDetailWithTrailer(id)

            override fun shouldFetch(data: DetailWithTrailer?): Boolean = data?.trailerItemsEntity == null || data.trailerItemsEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TrailerItems>>> = remoteDataSource.getDetailVideosMovie(id)

            override fun saveCallResult(data: List<TrailerItems>) {
                val json = Gson().toJson(data)
                val arrayTrailer = Gson().fromJson(json, Array<TrailerItemsEntity>::class.java)
                val trailerList = ArrayList<TrailerItemsEntity>()
                trailerList.addAll(arrayTrailer)
                localDataSource.insertDetailWithTrailerList(trailerList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(
        detailResponse: MutableLiveData<Resource<DetailResponse>>,
        id: Int
    ): LiveData<Resource<DetailResponse>> {
        return remoteDataSource.getDetailTvShow(detailResponse, id)
    }

    override fun getDetailCreditsTvShow(
        creditResponse: MutableLiveData<Resource<CreditResponse>>,
        id: Int
    ): LiveData<Resource<CreditResponse>> {
        return remoteDataSource.getDetailCreditsTvShow(creditResponse, id)
    }

    override fun getDetailRecommendationTvShow(
        recommendationResponse: MutableLiveData<Resource<RecommendationResponse>>,
        id: Int
    ): LiveData<Resource<RecommendationResponse>> {
        return remoteDataSource.getDetailRecommendationTvShow(recommendationResponse, id)
    }

    override fun getDetailVideosTvShow(
        trailerResponse: MutableLiveData<Resource<TrailerResponse>>,
        id: Int
    ): LiveData<Resource<TrailerResponse>> {
        return remoteDataSource.getDetailVideosTvShow(trailerResponse, id)
    }

    override fun getAllMovieFavorite(): LiveData<List<DetailEntity>> = localDataSource.getAllMovieFavorite()

    override fun deleteMovieFavorite(id: Int) = localDataSource.deleteMovieFavorite(id)


}