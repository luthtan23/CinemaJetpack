package com.luthtan.cinemajetpack.repository.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.NetworkBoundResource
import com.luthtan.cinemajetpack.model.bean.local.CastItemEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailEntity
import com.luthtan.cinemajetpack.model.bean.local.DetailWithCast
import com.luthtan.cinemajetpack.model.bean.response.detail.*
import com.luthtan.cinemajetpack.model.remote.ApiResponse
import com.luthtan.cinemajetpack.model.remote.LocalDataSource
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.util.AppExecutors
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class DetailRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : KoinComponent,
    DetailRepositorySource {

    override fun getCourseWithModules(id: Int): LiveData<Resource<DetailWithCast>> {
        return object : NetworkBoundResource<DetailWithCast, List<CastItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithCast> = localDataSource.getDetailWithCast(id)

            override fun shouldFetch(data: DetailWithCast?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<List<CastItem>>> = remoteDataSource.getDetailCreditsMovie(id)

            override fun saveCallResult(data: List<CastItem>) {
                val json = Gson().toJson(data)
                val arrayCast = Gson().fromJson(json, Array<CastItemEntity>::class.java).toList()
                localDataSource.insertCastItemList(arrayCast)
            }
        }.asLiveData()
    }

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

    override fun insertMovie(detailEntity: DetailEntity) {
        appExecutors.diskIO().execute{ localDataSource.addMovieFavorite(detailEntity) }
    }

    override fun updateMovieFavorite(detailEntity: DetailEntity, newState: Boolean) {
        appExecutors.diskIO().execute{ localDataSource.updateMovieFavorite(detailEntity, newState) }
    }

    override fun getDetailCreditsMovie(id: Int): LiveData<Resource<List<CastItemEntity>>> {
        return object : NetworkBoundResource<List<CastItemEntity>, List<CastItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<CastItemEntity>> = localDataSource.castItemList(id)

            override fun shouldFetch(data: List<CastItemEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CastItem>>> = remoteDataSource.getDetailCreditsMovie(id)

            override fun saveCallResult(data: List<CastItem>) {
                val json = Gson().toJson(data)
                val arrayCast = Gson().fromJson(json, Array<CastItemEntity>::class.java).toList()
                localDataSource.insertCastItemList(arrayCast)
            }
        }.asLiveData()
    }

    override fun getDetailRecommendationMovie(
        recommendationResponse: MutableLiveData<Resource<RecommendationResponse>>,
        id: Int
    ): LiveData<Resource<RecommendationResponse>> {
        return remoteDataSource.getDetailRecommendationMovie(recommendationResponse, id)
    }

    override fun getDetailVideosMovie(
        trailerResponse: MutableLiveData<Resource<TrailerResponse>>,
        id: Int
    ): LiveData<Resource<TrailerResponse>> {
        return remoteDataSource.getDetailVideosMovie(trailerResponse, id)
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

    override fun getAllMovieFavorite(): LiveData<List<DetailEntity>> = localDataSource.getMovieFavorite()

    override fun deleteMovieFavorite(id: Int) = localDataSource.deleteMovieFavorite(id)


}