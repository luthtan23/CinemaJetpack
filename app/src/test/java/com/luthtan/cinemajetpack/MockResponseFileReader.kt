package com.luthtan.cinemajetpack

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.bean.local.*
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.vo.Resource
import java.io.InputStreamReader

class MockResponseFileReader {

    fun getDummyMovieResponse() : MutableLiveData<Resource<MovieResponse>>{
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("movie_popular.json"))
        val json = reader.readText()
        reader.close()
        val response = Gson().fromJson(json, MovieResponse::class.java)
        val movieResponse = MutableLiveData<Resource<MovieResponse>>()
        movieResponse.value = Resource.success(response)
        return movieResponse
    }

    fun getDummyTvShowResponse() : MutableLiveData<Resource<TvShowResponse>>{
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("tv_show_popular.json"))
        val json = reader.readText()
        reader.close()
        val response = Gson().fromJson(json, TvShowResponse::class.java)
        val tvShowResponse = MutableLiveData<Resource<TvShowResponse>>()
        tvShowResponse.value = Resource.success(response)
        return tvShowResponse
    }

    fun getDummyDetailList() : MutableLiveData<Resource<List<DetailEntity>>>{
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("movie_list_response.json"))
        val json = reader.readText()
        reader.close()
        val detailEntity = Gson().fromJson(json, Array<DetailEntity>::class.java).toList()
        val mutableEntity = MutableLiveData<Resource<List<DetailEntity>>>()
        mutableEntity.value = Resource.success(detailEntity)
        return mutableEntity
    }

    fun getDummyTvShowDetailList() : MutableLiveData<Resource<List<DetailEntity>>>{
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("tv_show_list_response.json"))
        val json = reader.readText()
        reader.close()
        val detailEntity = Gson().fromJson(json, Array<DetailEntity>::class.java).toList()
        val mutableEntity = MutableLiveData<Resource<List<DetailEntity>>>()
        mutableEntity.value = Resource.success(detailEntity)
        return mutableEntity
    }

    fun getDummyDetail() : MutableLiveData<Resource<DetailEntity>>{
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("detail_response.json"))
        val json = reader.readText()
        reader.close()
        val detailEntity = Gson().fromJson(json, DetailEntity::class.java)
        val mutableEntity = MutableLiveData<Resource<DetailEntity>>()
        mutableEntity.value = Resource.success(detailEntity)
        return mutableEntity
    }

    fun getDummyTvShowDetail() : MutableLiveData<Resource<DetailEntity>>{
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("tv_show_detail_response.json"))
        val json = reader.readText()
        reader.close()
        val detailEntity = Gson().fromJson(json, DetailEntity::class.java)
        val mutableEntity = MutableLiveData<Resource<DetailEntity>>()
        mutableEntity.value = Resource.success(detailEntity)
        return mutableEntity
    }

    fun getDummyCast(): MutableLiveData<Resource<DetailWithCast>> {
        val detailEntity = getDummyDetail().value?.data
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("cast_response.json"))
        val json = reader.readText()
        reader.close()
        val arrayCast = Gson().fromJson(json, Array<CastItemEntity>::class.java).toList()
        val detailWithCast = MutableLiveData<Resource<DetailWithCast>>()
        detailWithCast.value = Resource.success(DetailWithCast(detailEntity!!, arrayCast))
        return detailWithCast
    }

    fun getDummyTvShowCast(): MutableLiveData<Resource<DetailWithCast>> {
        val detailEntity = getDummyTvShowDetail().value?.data
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("tv_show_cast_response.json"))
        val json = reader.readText()
        reader.close()
        val arrayCast = Gson().fromJson(json, Array<CastItemEntity>::class.java).toList()
        val detailWithCast = MutableLiveData<Resource<DetailWithCast>>()
        detailWithCast.value = Resource.success(DetailWithCast(detailEntity!!, arrayCast))
        return detailWithCast
    }

    fun getDummyRecommendation(): MutableLiveData<Resource<DetailWithRecommendation>> {
        val detailEntity = getDummyDetail().value?.data
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("movie_recommendation.json"))
        val json = reader.readText()
        reader.close()
        val arrayRecommendation =
            Gson().fromJson(json, Array<RecommendationItemsEntity>::class.java).toList()
        val detailWithRecommendation = MutableLiveData<Resource<DetailWithRecommendation>>()
        detailWithRecommendation.value = Resource.success(DetailWithRecommendation(detailEntity!!, arrayRecommendation))
        return detailWithRecommendation
    }

    fun getDummyTvShowRecommendation(): MutableLiveData<Resource<DetailWithRecommendation>> {
        val detailEntity = getDummyTvShowDetail().value?.data
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("tv_show_recommendation_response.json"))
        val json = reader.readText()
        reader.close()
        val arrayRecommendation =
            Gson().fromJson(json, Array<RecommendationItemsEntity>::class.java).toList()
        val detailWithRecommendation = MutableLiveData<Resource<DetailWithRecommendation>>()
        detailWithRecommendation.value = Resource.success(DetailWithRecommendation(detailEntity!!, arrayRecommendation))
        return detailWithRecommendation
    }

    fun getDummyVideos(): MutableLiveData<Resource<DetailWithTrailer>> {
        val detailEntity = getDummyDetail().value?.data
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("videos_response.json"))
        val json = reader.readText()
        reader.close()
        val arrayVideos =
            Gson().fromJson(json, Array<TrailerItemsEntity>::class.java).toList()
        val detailWithTrailer = MutableLiveData<Resource<DetailWithTrailer>>()
        detailWithTrailer.value = Resource.success(DetailWithTrailer(detailEntity!!, arrayVideos))
        return detailWithTrailer
    }

    fun getDummyTvShowVideos(): MutableLiveData<Resource<DetailWithTrailer>> {
        val detailEntity = getDummyTvShowDetail().value?.data
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("tv_show_videos_response.json"))
        val json = reader.readText()
        reader.close()
        val arrayVideos =
            Gson().fromJson(json, Array<TrailerItemsEntity>::class.java).toList()
        val detailWithTrailer = MutableLiveData<Resource<DetailWithTrailer>>()
        detailWithTrailer.value = Resource.success(DetailWithTrailer(detailEntity!!, arrayVideos))
        return detailWithTrailer
    }
}