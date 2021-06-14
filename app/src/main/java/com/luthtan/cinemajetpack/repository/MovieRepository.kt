package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.BuildConfig
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.model.remote.ApiService
import com.luthtan.cinemajetpack.model.remote.DataFetchCall
import org.koin.core.KoinComponent
import retrofit2.Response

class MovieRepository(private val apiService: ApiService) : KoinComponent {

    fun getPopularMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMoviePopular(BuildConfig.TOKEN)
            }
        }.execute()
    }


    fun getTopRatedMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieTopRated(BuildConfig.TOKEN)
            }
        }.execute()
    }

    fun getUpcomingMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieUpComing(BuildConfig.TOKEN)
            }
        }.execute()
    }

    fun getNowPlayingMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieNowPlaying(BuildConfig.TOKEN)
            }
        }.execute()
    }

    fun getSimilarMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>,
        id: Int
    ) {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieSimilar(BuildConfig.TOKEN, id)
            }
        }.execute()
    }


}