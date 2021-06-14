package com.luthtan.cinemajetpack.model.remote

object ApiConstant {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_TIME_OUT: Long = 6000
    const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500/"

    //movie
    const val GET_MOVIE_POPULAR = "movie/popular"
    const val GET_MOVIE_TOP_RATED = "movie/top_rated"
    const val GET_MOVIE_NOW_PLAYING = "movie/now_playing"
    const val GET_MOVIE_UPCOMING = "movie/upcoming"
    const val GET_MOVIE_SIMILAR = "movie/{id}/similar"
    const val GET_MOVIE_DETAIL = "movie/{id}"
    const val GET_MOVIE_CREDITS = "movie/{id}/credits"
    const val GET_MOVIE_RECOMMENDATION = "movie/{id}/recommendations"
    const val GET_MOVIE_VIDEOS = "movie/{id}/videos"

    //tvshow
    const val GET_TVSHOW_POPULAR = "tv/popular"
    const val GET_TVSHOW_TOP_RATED = "tv/top_rated"
    const val GET_TVSHOW_ONTHEAIR = "tv/on_the_air"
    const val GET_TVSHOW_AIRING_TODAY = "tv/airing_today"
    const val GET_TVSHOW_SIMILAR = "tv/{id}/similar"
    const val GET_TVSHOW_DETAIL = "tv/{id}"
    const val GET_TVSHOW_CREDITS = "tv/{id}/credits"
    const val GET_TVSHOW_RECOMMENDATION = "tv/{id}/recommendations"
    const val GET_TVSHOW_VIDEOS = "tv/{id}/videos"

    //youtube
    const val YOUTUBE_URL = "https://www.youtube.com/watch?v="

    //login
    const val GET_TOKEN_LOGIN = "authentication/token/new"
    const val GET_VALIDATE_LOGIN = "authentication/token/validate_with_login"


}