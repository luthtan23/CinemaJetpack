package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_db")
data class DetailEntity(
    @field:SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "detailId")
    val detailId: Int,

    @field:SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    val imdbId: String,

    @field:SerializedName("revenue")
    @ColumnInfo(name ="revenue")
    val revenue: Int,

    @field:SerializedName("budget")
    @ColumnInfo(name = "budget")
    val budget: Int,

    @field:SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runtime: Int,

    @field:SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    val tagline: String,

    @field:SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    val homepage: String,

    @field:SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String,

    @field:SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String,

    @field:SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    var originalLanguage: String,

    @field:SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    var originalTitle: String,

    @field:SerializedName("video")
    @ColumnInfo(name = "video")
    var video: Boolean,

    @field:SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String,

    @field:SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @field:SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,

    @field:SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<GenresItemEntity>,

    @field:SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @field:SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @field:SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @field:SerializedName("adult")
    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @field:SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @ColumnInfo(name = "isMovieFavorite")
    var isMovieFavorite: Boolean = false
)