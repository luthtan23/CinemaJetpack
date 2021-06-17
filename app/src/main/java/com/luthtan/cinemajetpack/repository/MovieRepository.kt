package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.BuildConfig
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.model.remote.ApiService
import com.luthtan.cinemajetpack.model.remote.DataFetchCall
import com.luthtan.cinemajetpack.repository.movie.MovieRepositoryListener
import org.koin.core.KoinComponent
import retrofit2.Response

class MovieRepository(private val apiService: ApiService) : KoinComponent, MovieRepositoryListener {

    override suspend fun getPopularMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse> {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMoviePopular(BuildConfig.TOKEN)
            }
        }.execute()
        return movieResponse
    }

    override suspend fun getTopRatedMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse> {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieTopRated(BuildConfig.TOKEN)
            }
        }.execute()
        return movieResponse
    }

    override suspend fun getUpcomingMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse> {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieUpComing(BuildConfig.TOKEN)
            }
        }.execute()
        return movieResponse
    }

    override suspend fun getNowPlayingMovie(
        movieResponse: MutableLiveData<MovieResponse>,
        errorResponse: MutableLiveData<String>
    ): MutableLiveData<MovieResponse> {
        object : DataFetchCall<MovieResponse>(movieResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieNowPlaying(BuildConfig.TOKEN)
            }
        }.execute()
        return movieResponse
    }


}