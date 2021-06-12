package com.luthtan.cinemajetpack.model.remote

import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(ApiConstant.GET_MOVIE_POPULAR)
    suspend fun getMoviePopular(@Header("Authorization") token: String): Response<MovieResponse>

    @GET(ApiConstant.GET_MOVIE_NOW_PLAYING)
    suspend fun getMovieNowPlaying(@Header("Authorization") token: String): Response<MovieResponse>

    @GET(ApiConstant.GET_MOVIE_TOP_RATED)
    suspend fun getMovieTopRated(@Header("Authorization") token: String): Response<MovieResponse>

    @GET(ApiConstant.GET_MOVIE_UPCOMING)
    suspend fun getMovieUpComing(@Header("Authorization") token: String): Response<MovieResponse>

    @GET(ApiConstant.GET_MOVIE_SIMILAR)
    suspend fun getMovieSimilar(@Header("Authorization") token: String, @Path("id") id: Int): Response<MovieResponse>

    @GET(ApiConstant.GET_MOVIE_DETAIL)
    suspend fun getMovieDetail(@Header("Authorization") token: String, @Path("id") id: Int): Response<DetailResponse>

    @GET(ApiConstant.GET_MOVIE_CREDITS)
    suspend fun getMovieDetailCredits(@Header("Authorization") token: String, @Path("id") id: Int): Response<CreditResponse>

    @GET(ApiConstant.GET_MOVIE_RECOMMENDATION)
    suspend fun getMovieDetailRecommendation(@Header("Authorization") token: String, @Path("id") id: Int): Response<RecommendationResponse>

    @GET(ApiConstant.GET_MOVIE_VIDEOS)
    suspend fun getMovieDetailVideos(@Header("Authorization") token: String, @Path("id") id: Int): Response<TrailerResponse>


    @GET(ApiConstant.GET_TVSHOW_POPULAR)
    suspend fun getTvShowPopular(@Header("Authorization") token: String): Response<TvShowResponse>

    @GET(ApiConstant.GET_TVSHOW_AIRING_TODAY)
    suspend fun getTvShowNowPlaying(@Header("Authorization") token: String): Response<TvShowResponse>

    @GET(ApiConstant.GET_TVSHOW_TOP_RATED)
    suspend fun getTvShowTopRated(@Header("Authorization") token: String): Response<TvShowResponse>

    @GET(ApiConstant.GET_TVSHOW_ONTHEAIR)
    suspend fun getTvShowUpComing(@Header("Authorization") token: String): Response<TvShowResponse>

    @GET(ApiConstant.GET_TVSHOW_SIMILAR)
    suspend fun getTvShowSimilar(@Header("Authorization") token: String, @Path("id") id: Int): Response<TvShowResponse>

    @GET(ApiConstant.GET_TVSHOW_DETAIL)
    suspend fun getTvShowDetail(@Header("Authorization") token: String, @Path("id") id: Int): Response<DetailResponse>

    @GET(ApiConstant.GET_TVSHOW_CREDITS)
    suspend fun getTvShowDetailCredits(@Header("Authorization") token: String, @Path("id") id: Int): Response<CreditResponse>

    @GET(ApiConstant.GET_TVSHOW_RECOMMENDATION)
    suspend fun getTvShowDetailRecommendation(@Header("Authorization") token: String, @Path("id") id: Int): Response<RecommendationResponse>

    @GET(ApiConstant.GET_TVSHOW_VIDEOS)
    suspend fun getTvShowDetailVideos(@Header("Authorization") token: String, @Path("id") id: Int): Response<TrailerResponse>

    @GET(ApiConstant.GET_TOKEN_LOGIN)
    suspend fun getTokenLogin(@Header("Authorization") token: String): Response<TokenResponse>

    @POST(ApiConstant.GET_VALIDATE_LOGIN)
    suspend fun getValidateLogin(@Header("Authorization") token: String, @Body validateRequest: ValidateRequest): Response<ValidateResponse>


}