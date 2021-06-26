package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "recommendationEntity",
    primaryKeys = ["recommendationId", "detailId"],
    foreignKeys = [ForeignKey(
        entity = DetailEntity::class,
        parentColumns = ["detailId"],
        childColumns = ["detailId"]
    )],
    indices = [Index(value = ["recommendationId"]),
        Index(value = ["detailId"])]
)
data class RecommendationItemsEntity(
    @field:SerializedName("id")
    @ColumnInfo(defaultValue = "", name = "recommendationId")
    val recommendationId: Int,

    @ColumnInfo(defaultValue = "", name = "detailId")
    var detailId: Int,

    @field:SerializedName("first_air_date")
    @ColumnInfo(defaultValue = "", name = "first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    @ColumnInfo(defaultValue = "", name = "overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    @ColumnInfo(defaultValue = "", name = "original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("poster_path")
    @ColumnInfo(defaultValue = "", name = "poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    @ColumnInfo(defaultValue = "", name = "backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("media_type")
    @ColumnInfo(defaultValue = "", name = "media_type")
    val mediaType: String? = null,

    @field:SerializedName("original_name")
    @ColumnInfo(defaultValue = "", name = "original_name")
    val originalName: String? = null,

    @field:SerializedName("popularity")
    @ColumnInfo(defaultValue = "", name = "popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    @ColumnInfo(defaultValue = "", name = "vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    @ColumnInfo(defaultValue = "", name = "name")
    val name: String? = null,

    @field:SerializedName("adult")
    @ColumnInfo(defaultValue = "", name = "adult")
    val adult: Boolean? = null,

    @field:SerializedName("vote_count")
    @ColumnInfo(defaultValue = "", name = "vote_count")
    val voteCount: Int? = null
)
