package com.luthtan.cinemajetpack.util

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.luthtan.cinemajetpack.model.bean.local.*
import com.luthtan.cinemajetpack.vo.Resource
import java.io.IOException
import java.nio.charset.StandardCharsets


object DummyDataJson {

    private fun loadDataMoviePopularJson(context: Context, nameFile: String): String? {
        val json: String?
        try {
            val inputStream = context.assets.open(nameFile)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }

    fun getDummyDetail(): MutableLiveData<Resource<DetailEntity>> {
        val genresItemEntity = ArrayList<GenresItemEntity>()
        genresItemEntity.add(GenresItemEntity(35, "Comedy"))
        genresItemEntity.add(GenresItemEntity(18, "Drama"))
        genresItemEntity.add(GenresItemEntity(10749, "Romance"))
        val liveDataDetailEntity = MutableLiveData<Resource<DetailEntity>>()
        val detailEntityList = ArrayList<DetailEntity>()
        val detailEntity = DetailEntity(
            19913,
            "tt1022603",
            60781545,
            7500000,
            95,
            "This is not a love story. This is a story about love.",
            "https://www.foxmovies.com/movies/500-days-of-summer",
            "Released",
            "Tom, greeting-card writer and hopeless romantic, is caught completely off-guard when his girlfriend, Summer, " +
                    "suddenly dumps him. He reflects on their 500 days together to try to figure out where their love affair went sour, " +
                    "and in doing so, Tom rediscovers his true passions in life.",
            "en",
            "(500) Days of Summer",
            false,
            "(500) Days of Summer",
            "/f9mbM0YMLpYemcWx6o2WeiYQLDP.jpg",
            "/x0emyxKfPMfxuW71xrzV07cmg1e.jpg",
            genresItemEntity,
            "2009-07-17",
            29.787,
            7.3,
            false,
            7665,
            "", "",
            isMovieFavorite = true, isTvShowFavorite = false
        )
        detailEntityList.add(detailEntity)
        liveDataDetailEntity.value = Resource.success(detailEntity)
        return liveDataDetailEntity
    }

    fun getDummyCast(context: Context): MutableLiveData<Resource<DetailWithCast>> {
        val detailEntity = getDummyDetail().value?.data
        val json = loadDataMoviePopularJson(context, "cast_response.json")
        val arrayCast = Gson().fromJson(json, Array<CastItemEntity>::class.java).toList()
        val detailWithCast = MutableLiveData<Resource<DetailWithCast>>()
        detailWithCast.value = Resource.success(DetailWithCast(detailEntity!!, arrayCast))
        return detailWithCast
    }

    fun getDummyRecommendation(context: Context): MutableLiveData<Resource<DetailWithRecommendation>> {
        val detailEntity = getDummyDetail().value?.data
        val json = loadDataMoviePopularJson(context, "movie_recommendation.json")
        val arrayRecommendation =
            Gson().fromJson(json, Array<RecommendationItemsEntity>::class.java).toList()
        val detailWithRecommendation = MutableLiveData<Resource<DetailWithRecommendation>>()
        detailWithRecommendation.value =
            Resource.success(DetailWithRecommendation(detailEntity!!, arrayRecommendation))
        return detailWithRecommendation
    }

    fun getDummyVideos(context: Context): MutableLiveData<Resource<DetailWithTrailer>> {
        val detailEntity = getDummyDetail().value?.data
        val json = loadDataMoviePopularJson(context, "videos_response.json")
        val arrayVideos =
            Gson().fromJson(json, Array<TrailerItemsEntity>::class.java).toList()
        val detailWithTrailer = MutableLiveData<Resource<DetailWithTrailer>>()
        detailWithTrailer.value = Resource.success(DetailWithTrailer(detailEntity!!, arrayVideos))
        return detailWithTrailer
    }

}