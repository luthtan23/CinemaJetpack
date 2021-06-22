package com.luthtan.cinemajetpack.repository.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.model.remote.RemoteDataSource
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class MovieRepository(private val remoteDataResource: RemoteDataSource) : KoinComponent,
    MovieRepositorySource {

    override fun getPopularMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        return remoteDataResource.getPopularMovie(movieResponse)
    }

    override fun getTopRatedMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        return remoteDataResource.getTopRatedMovie(movieResponse)
    }

    override fun getUpcomingMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        return remoteDataResource.getUpcomingMovie(movieResponse)
    }

    override fun getNowPlayingMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        return remoteDataResource.getNowPlayingMovie(movieResponse)
    }


}