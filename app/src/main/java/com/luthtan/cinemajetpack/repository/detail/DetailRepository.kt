package com.luthtan.cinemajetpack.repository.detail

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.NetworkBoundResource
import com.luthtan.cinemajetpack.model.bean.local.*
import com.luthtan.cinemajetpack.model.bean.response.detail.*
import com.luthtan.cinemajetpack.model.remote.ApiResponse
import com.luthtan.cinemajetpack.model.remote.LocalDataSource
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.util.AppExecutors
import com.luthtan.cinemajetpack.util.Utils
import com.luthtan.cinemajetpack.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class DetailRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : KoinComponent,
    DetailRepositorySource {

    override fun getDetailMovie(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBoundResource<DetailEntity, DetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> =
                localDataSource.retrieveMovieFavorite(id)

            override fun shouldFetch(data: DetailEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<DetailResponse>> =
                remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: DetailResponse) {
                val json = Gson().toJson(data)
                val movieEntity = Gson().fromJson(json, DetailEntity::class.java)
                localDataSource.addMovieFavorite(movieEntity)
            }
        }.asLiveData()
    }

    override fun getAllMovieFavoriteList(): LiveData<PagedList<DetailEntity>> {
        return LivePagedListBuilder(localDataSource.getAllMovieFavoriteList(), Utils.configPaging).build()
    }

    override fun getDetailWithCast(id: Int): LiveData<Resource<DetailWithCast>> {
        return object : NetworkBoundResource<DetailWithCast, List<CastItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithCast> =
                localDataSource.getDetailWithCast(id)

            override fun shouldFetch(data: DetailWithCast?): Boolean =
                data?.castItemEntity == null || data.castItemEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CastItem>>> =
                remoteDataSource.getDetailCreditsMovie(id)

            override fun saveCallResult(data: List<CastItem>) {
                val json = Gson().toJson(data)
                val arrayCast = Gson().fromJson(json, Array<CastItemEntity>::class.java)
                val castList = ArrayList<CastItemEntity>()
                castList.addAll(arrayCast)
                try {
                    GlobalScope.launch(Dispatchers.IO) {
                        localDataSource.insertDetailWithCastList(castList)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.asLiveData()
    }

    override fun insertMovie(detailEntity: DetailEntity) {
        appExecutors.diskIO().execute { localDataSource.addMovieFavorite(detailEntity) }
    }

    override fun updateMovieFavorite(detailEntity: DetailEntity, newState: Boolean) {
        appExecutors.diskIO()
            .execute { localDataSource.updateMovieFavorite(detailEntity, newState) }
    }

    override fun getDetailWithRecommendation(id: Int): LiveData<Resource<DetailWithRecommendation>> {
        return object :
            NetworkBoundResource<DetailWithRecommendation, List<RecommendationItems>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithRecommendation> =
                localDataSource.getDetailWithRecommendation(id)

            override fun shouldFetch(data: DetailWithRecommendation?): Boolean =
                data?.recommendationItemsEntity == null || data.recommendationItemsEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<RecommendationItems>>> =
                remoteDataSource.getDetailRecommendationMovie(id)

            override fun saveCallResult(data: List<RecommendationItems>) {
                val json = Gson().toJson(data)
                val arrayRecommendation =
                    Gson().fromJson(json, Array<RecommendationItemsEntity>::class.java)
                val recommendationList = ArrayList<RecommendationItemsEntity>()
                recommendationList.addAll(arrayRecommendation)
                try {
                    GlobalScope.launch(Dispatchers.IO) {
                        localDataSource.insertDetailWithRecommendationList(recommendationList)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.asLiveData()
    }

    override fun getDetailWithTrailer(id: Int): LiveData<Resource<DetailWithTrailer>> {
        return object : NetworkBoundResource<DetailWithTrailer, List<TrailerItems>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithTrailer> =
                localDataSource.getDetailWithTrailer(id)

            override fun shouldFetch(data: DetailWithTrailer?): Boolean =
                data?.trailerItemsEntity == null || data.trailerItemsEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TrailerItems>>> =
                remoteDataSource.getDetailVideosMovie(id)

            override fun saveCallResult(data: List<TrailerItems>) {
                val json = Gson().toJson(data)
                val arrayTrailer = Gson().fromJson(json, Array<TrailerItemsEntity>::class.java)
                val trailerList = ArrayList<TrailerItemsEntity>()
                trailerList.addAll(arrayTrailer)
                try {
                    GlobalScope.launch(Dispatchers.IO) {
                        localDataSource.insertDetailWithTrailerList(trailerList)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.asLiveData()
    }

    override fun getAllMovieFavorite(): LiveData<List<DetailEntity>> =
        localDataSource.getAllMovieFavorite()

    override fun deleteMovieFavorite(id: Int) = localDataSource.deleteMovieFavorite(id)

    override fun getDetailTvShow(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBoundResource<DetailEntity, DetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> =
                localDataSource.retrieveTvShowFavorite(id)

            override fun shouldFetch(data: DetailEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<DetailResponse>> =
                remoteDataSource.getDetailTvShow(id)

            override fun saveCallResult(data: DetailResponse) {
                val json = Gson().toJson(data)
                val movieEntity = Gson().fromJson(json, DetailEntity::class.java)
                localDataSource.addTvShowFavorite(movieEntity)
            }
        }.asLiveData()
    }

    override fun getAllTvShowFavoriteList(): LiveData<PagedList<DetailEntity>> {
        return LivePagedListBuilder(localDataSource.getAllTvShowFavoriteList(), Utils.configPaging).build()
    }

    override fun getTvShowDetailWithCast(id: Int): LiveData<Resource<DetailWithCast>> {
        return object : NetworkBoundResource<DetailWithCast, List<CastItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithCast> =
                localDataSource.getTvShowDetailWithCast(id)

            override fun shouldFetch(data: DetailWithCast?): Boolean =
                data?.castItemEntity == null || data.castItemEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CastItem>>> =
                remoteDataSource.getDetailCreditsTvShow(id)

            override fun saveCallResult(data: List<CastItem>) {
                val json = Gson().toJson(data)
                val arrayCast = Gson().fromJson(json, Array<CastItemEntity>::class.java)
                val castList = ArrayList<CastItemEntity>()
                castList.addAll(arrayCast)
                try {
                    GlobalScope.launch(Dispatchers.IO) {
                        localDataSource.insertTvShowDetailWithCastList(castList)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.asLiveData()
    }

    override fun insertTvShow(detailEntity: DetailEntity) {
        appExecutors.diskIO().execute { localDataSource.addTvShowFavorite(detailEntity) }
    }

    override fun updateTvShowFavorite(detailEntity: DetailEntity, newState: Boolean) {
        appExecutors.diskIO()
            .execute { localDataSource.updateTvShowFavorite(detailEntity, newState) }
    }

    override fun getTvShowDetailWithRecommendation(id: Int): LiveData<Resource<DetailWithRecommendation>> {
        return object :
            NetworkBoundResource<DetailWithRecommendation, List<RecommendationItems>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithRecommendation> =
                localDataSource.getTvShowDetailWithRecommendation(id)

            override fun shouldFetch(data: DetailWithRecommendation?): Boolean =
                data?.recommendationItemsEntity == null || data.recommendationItemsEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<RecommendationItems>>> =
                remoteDataSource.getDetailRecommendationTvShow(id)

            override fun saveCallResult(data: List<RecommendationItems>) {
                val json = Gson().toJson(data)
                val arrayRecommendation =
                    Gson().fromJson(json, Array<RecommendationItemsEntity>::class.java)
                val recommendationList = ArrayList<RecommendationItemsEntity>()
                recommendationList.addAll(arrayRecommendation)
                try {
                    GlobalScope.launch(Dispatchers.IO) {
                        localDataSource.insertTvShowDetailWithRecommendationList(recommendationList)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.asLiveData()
    }

    override fun getTvShowDetailWithTrailer(id: Int): LiveData<Resource<DetailWithTrailer>> {
        return object : NetworkBoundResource<DetailWithTrailer, List<TrailerItems>>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailWithTrailer> =
                localDataSource.getTvShowDetailWithTrailer(id)

            override fun shouldFetch(data: DetailWithTrailer?): Boolean =
                data?.trailerItemsEntity == null || data.trailerItemsEntity.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TrailerItems>>> =
                remoteDataSource.getDetailVideosTvShow(id)

            override fun saveCallResult(data: List<TrailerItems>) {
                val json = Gson().toJson(data)
                val arrayTrailer = Gson().fromJson(json, Array<TrailerItemsEntity>::class.java)
                val trailerList = ArrayList<TrailerItemsEntity>()
                trailerList.addAll(arrayTrailer)
                try {
                    GlobalScope.launch(Dispatchers.IO) {
                        localDataSource.insertTvShowDetailWithTrailerList(trailerList)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.asLiveData()
    }

    override fun getAllTvShowFavorite(): LiveData<List<DetailEntity>> =
        localDataSource.getAllTvShowFavorite()

    override fun deleteTvShowFavorite(id: Int) = localDataSource.deleteTvShowFavorite(id)


}