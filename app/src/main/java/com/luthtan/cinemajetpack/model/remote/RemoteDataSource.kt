package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.BuildConfig
import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.model.bean.response.detail.CreditResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.DetailResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.RecommendationResponse
import com.luthtan.cinemajetpack.model.bean.response.detail.TrailerResponse
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.model.bean.response.movie.MovieResponse
import com.luthtan.cinemajetpack.model.bean.response.tvshow.TvShowResponse
import com.luthtan.cinemajetpack.vo.Resource
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    fun getPopularMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): LiveData<Resource<MovieResponse>> {
        object : DataFetchCall<MovieResponse>(movieResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMoviePopular(BuildConfig.TOKEN)
            }
        }.execute()
        return movieResponse
    }

    fun getTopRatedMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): MutableLiveData<Resource<MovieResponse>> {
        object : DataFetchCall<MovieResponse>(movieResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieTopRated(BuildConfig.TOKEN)
            }
        }.execute()
        return movieResponse
    }

    fun getUpcomingMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): MutableLiveData<Resource<MovieResponse>> {
        object : DataFetchCall<MovieResponse>(movieResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieUpComing(BuildConfig.TOKEN)
            }
        }.execute()
        return movieResponse
    }

    fun getNowPlayingMovie(movieResponse: MutableLiveData<Resource<MovieResponse>>): MutableLiveData<Resource<MovieResponse>> {
        object : DataFetchCall<MovieResponse>(movieResponse) {
            override suspend fun createCallAsync(): Response<MovieResponse> {
                return apiService.getMovieNowPlaying(BuildConfig.TOKEN)
            }
        }.execute()
        return movieResponse
    }

    fun getPopularTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): MutableLiveData<Resource<TvShowResponse>> {
        object : DataFetchCall<TvShowResponse>(tvShowResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowPopular(BuildConfig.TOKEN)
            }
        }.execute()
        return tvShowResponse
    }

    fun getNowPlayingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): MutableLiveData<Resource<TvShowResponse>> {
        object : DataFetchCall<TvShowResponse>(tvShowResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowNowPlaying(BuildConfig.TOKEN)
            }
        }.execute()
        return tvShowResponse
    }

    fun getTopRatedTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): MutableLiveData<Resource<TvShowResponse>> {
        object : DataFetchCall<TvShowResponse>(tvShowResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowTopRated(BuildConfig.TOKEN)
            }
        }.execute()
        return tvShowResponse
    }

    fun getUpcomingTvShow(tvShowResponse: MutableLiveData<Resource<TvShowResponse>>): MutableLiveData<Resource<TvShowResponse>> {
        object : DataFetchCall<TvShowResponse>(tvShowResponse) {
            override suspend fun createCallAsync(): Response<TvShowResponse> {
                return apiService.getTvShowUpComing(BuildConfig.TOKEN)
            }
        }.execute()
        return tvShowResponse
    }

    fun getDetailMovie(
        detailResponse: MutableLiveData<Resource<DetailResponse>>,
        id: Int
    ): MutableLiveData<Resource<DetailResponse>> {
        object : DataFetchCall<DetailResponse>(detailResponse) {
            override suspend fun createCallAsync(): Response<DetailResponse> {
                return apiService.getMovieDetail(BuildConfig.TOKEN, id)
            }
        }.execute()
        return detailResponse
    }

    fun getDetailCreditsMovie(
        creditResponse: MutableLiveData<Resource<CreditResponse>>,
        id: Int
    ): MutableLiveData<Resource<CreditResponse>> {
        object : DataFetchCall<CreditResponse>(creditResponse) {
            override suspend fun createCallAsync(): Response<CreditResponse> {
                return apiService.getMovieDetailCredits(BuildConfig.TOKEN, id)
            }
        }.execute()
        return creditResponse
    }

    fun getDetailRecommendationMovie(
        recommendationResponse: MutableLiveData<Resource<RecommendationResponse>>,
        id: Int
    ): MutableLiveData<Resource<RecommendationResponse>> {
        object : DataFetchCall<RecommendationResponse>(recommendationResponse) {
            override suspend fun createCallAsync(): Response<RecommendationResponse> {
                return apiService.getMovieDetailRecommendation(BuildConfig.TOKEN, id)
            }
        }.execute()
        return recommendationResponse
    }

    fun getDetailVideosMovie(
        trailerResponse: MutableLiveData<Resource<TrailerResponse>>,
        id: Int
    ): MutableLiveData<Resource<TrailerResponse>> {
        object : DataFetchCall<TrailerResponse>(trailerResponse) {
            override suspend fun createCallAsync(): Response<TrailerResponse> {
                return apiService.getMovieDetailVideos(BuildConfig.TOKEN, id)
            }
        }.execute()
        return trailerResponse
    }

    fun getDetailTvShow(
        detailResponse: MutableLiveData<Resource<DetailResponse>>,
        id: Int
    ): MutableLiveData<Resource<DetailResponse>> {
        object : DataFetchCall<DetailResponse>(detailResponse) {
            override suspend fun createCallAsync(): Response<DetailResponse> {
                return apiService.getTvShowDetail(BuildConfig.TOKEN, id)
            }
        }.execute()
        return detailResponse
    }

    fun getDetailCreditsTvShow(
        creditResponse: MutableLiveData<Resource<CreditResponse>>,
        id: Int
    ): MutableLiveData<Resource<CreditResponse>> {
        object : DataFetchCall<CreditResponse>(creditResponse) {
            override suspend fun createCallAsync(): Response<CreditResponse> {
                return apiService.getTvShowDetailCredits(BuildConfig.TOKEN, id)
            }
        }.execute()
        return creditResponse
    }

    fun getDetailRecommendationTvShow(
        recommendationResponse: MutableLiveData<Resource<RecommendationResponse>>,
        id: Int
    ): MutableLiveData<Resource<RecommendationResponse>> {
        object : DataFetchCall<RecommendationResponse>(recommendationResponse) {
            override suspend fun createCallAsync(): Response<RecommendationResponse> {
                return apiService.getTvShowDetailRecommendation(BuildConfig.TOKEN, id)
            }
        }.execute()
        return recommendationResponse
    }

    fun getDetailVideosTvShow(
        trailerResponse: MutableLiveData<Resource<TrailerResponse>>,
        id: Int
    ): MutableLiveData<Resource<TrailerResponse>> {
        object : DataFetchCall<TrailerResponse>(trailerResponse) {
            override suspend fun createCallAsync(): Response<TrailerResponse> {
                return apiService.getTvShowDetailVideos(BuildConfig.TOKEN, id)
            }
        }.execute()
        return trailerResponse
    }

    fun getTokenLogin(tokenResponse: MutableLiveData<Resource<TokenResponse>>): MutableLiveData<Resource<TokenResponse>> {
        object : DataFetchCall<TokenResponse>(tokenResponse) {
            override suspend fun createCallAsync(): Response<TokenResponse> {
                return apiService.getTokenLogin(BuildConfig.TOKEN)
            }
        }.execute()
        return tokenResponse
    }

    fun getValidateLogin(
        validateResponse: MutableLiveData<Resource<ValidateResponse>>,
        validateRequest: ValidateRequest
    ): MutableLiveData<Resource<ValidateResponse>> {
        object : DataFetchCall<ValidateResponse>(validateResponse) {
            override suspend fun createCallAsync(): Response<ValidateResponse> {
                return apiService.getValidateLogin(BuildConfig.TOKEN, validateRequest)
            }
        }.execute()
        return validateResponse
    }
}