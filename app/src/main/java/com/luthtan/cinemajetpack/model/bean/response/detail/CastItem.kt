package com.luthtan.cinemajetpack.model.bean.response.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CastItem(
    @field:SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @field:SerializedName("cast_id")
    @ColumnInfo(name = "cast_id")
    val castId: Int,

    @field:SerializedName("character")
    @ColumnInfo(name = "character")
    val character: String,

    @field:SerializedName("gender")
    @ColumnInfo(name = "gender")
    val gender: Int,

    @field:SerializedName("credit_id")
    @ColumnInfo(name = "credit_id")
    val creditId: String,

    @field:SerializedName("known_for_department")
    @ColumnInfo(name = "known_for_department")
    val knownForDepartment: String,

    @field:SerializedName("original_name")
    @ColumnInfo(name = "original_name")
    val originalName: String,

    @field:SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @field:SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @field:SerializedName("profile_path")
    @ColumnInfo(name = "profile_path")
    val profilePath: String,

    @field:SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @field:SerializedName("order")
    @ColumnInfo(name = "order")
    val order: Int
)