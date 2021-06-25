package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_db")
data class DetailEntity(
    @field:SerializedName("id")
    @PrimaryKey
    @ColumnInfo(defaultValue = "", name = "detailId")
    val detailId: Int,

    @field:SerializedName("imdb_id")
    @ColumnInfo(defaultValue = "", name = "imdb_id")
    val imdbId: String,

    @field:SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    val revenue: Int,

    @field:SerializedName("budget")
    @ColumnInfo(defaultValue = "", name = "budget")
    val budget: Int,

    @field:SerializedName("runtime")
    @ColumnInfo(defaultValue = "", name = "runtime")
    val runtime: Int,

    @field:SerializedName("tagline")
    @ColumnInfo(defaultValue = "", name = "tagline")
    val tagline: String,

    @field:SerializedName("homepage")
    @ColumnInfo(defaultValue = "", name = "homepage")
    val homepage: String,

    @field:SerializedName("status")
    @ColumnInfo(defaultValue = "", name = "status")
    val status: String,

    @field:SerializedName("overview")
    @ColumnInfo(defaultValue = "", name = "overview")
    var overview: String,

    @field:SerializedName("original_language")
    @ColumnInfo(defaultValue = "", name = "original_language")
    var originalLanguage: String,

    @field:SerializedName("original_title")
    @ColumnInfo(defaultValue = "", name = "original_title")
    var originalTitle: String,

    @field:SerializedName("video")
    @ColumnInfo(defaultValue = "", name = "video")
    var video: Boolean,

    @field:SerializedName("title")
    @ColumnInfo(defaultValue = "", name = "title")
    var title: String,

    @field:SerializedName("poster_path")
    @ColumnInfo(defaultValue = "", name = "poster_path")
    var posterPath: String,

    @field:SerializedName("backdrop_path")
    @ColumnInfo(defaultValue = "", name = "backdrop_path")
    var backdropPath: String,

    @field:SerializedName("genres")
    @ColumnInfo(defaultValue = "", name = "genres")
    val genres: List<GenresItemEntity>,

    @field:SerializedName("release_date")
    @ColumnInfo(defaultValue = "", name = "release_date")
    var releaseDate: String,

    @field:SerializedName("popularity")
    @ColumnInfo(defaultValue = "", name = "popularity")
    var popularity: Double,

    @field:SerializedName("vote_average")
    @ColumnInfo(defaultValue = "", name = "vote_average")
    var voteAverage: Double,

    @field:SerializedName("adult")
    @ColumnInfo(defaultValue = "", name = "adult")
    var adult: Boolean,

    @field:SerializedName("vote_count")
    @ColumnInfo(defaultValue = "", name = "vote_count")
    var voteCount: Int,

    @field:SerializedName("name")
    @ColumnInfo(defaultValue = "", name = "name")
    val name: String,

    @field:SerializedName("first_air_date")
    @ColumnInfo(defaultValue = "", name = "first_air_date")
    val firstAirDate: String,

    @ColumnInfo(defaultValue = "", name = "isMovieFavorite")
    var isMovieFavorite: Boolean = false,

    @ColumnInfo(defaultValue = "", name = "isTvShowFavorite")
    var isTvShowFavorite: Boolean = false
)