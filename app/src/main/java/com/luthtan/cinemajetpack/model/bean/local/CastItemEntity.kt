package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "castEntity",
    primaryKeys = ["castId", "detailId"],
    foreignKeys = [ForeignKey(
        entity = DetailEntity::class,
        parentColumns = ["detailId"],
        childColumns = ["detailId"]
    )],
    indices = [Index(value = ["castId"]),
        Index(value = ["detailId"])]
)
data class CastItemEntity(
    @field:SerializedName("id")
    @ColumnInfo(defaultValue = "", name = "castId")
    var castId: Int,

    @ColumnInfo(defaultValue = "", name = "detailId")
    var detailId: Int,

    @field:SerializedName("character")
    @ColumnInfo(defaultValue = "", name = "character")
    var character: String? = "",

    @field:SerializedName("gender")
    @ColumnInfo(defaultValue = "", name = "gender")
    var gender: Int? = 0,

    @field:SerializedName("credit_id")
    @ColumnInfo(defaultValue = "", name = "credit_id")
    var creditId: String? = "",

    @field:SerializedName("known_for_department")
    @ColumnInfo(defaultValue = "", name = "known_for_department")
    var knownForDepartment: String? = "",

    @field:SerializedName("original_name")
    @ColumnInfo(defaultValue = "", name = "original_name")
    var originalName: String? = "",

    @field:SerializedName("popularity")
    @ColumnInfo(defaultValue = "", name = "popularity")
    var popularity: Double? = 0.0,

    @field:SerializedName("name")
    @ColumnInfo(defaultValue = "", name = "name")
    var name: String? = "",

    @field:SerializedName("profile_path")
    @ColumnInfo(defaultValue = "", name = "profile_path")
    var profilePath: String? = "",

    @field:SerializedName("adult")
    @ColumnInfo(defaultValue = "", name = "adult")
    var adult: Boolean? = false,

    @field:SerializedName("order")
    @ColumnInfo(defaultValue = "", name = "order")
    var order: Int? = 0
)
