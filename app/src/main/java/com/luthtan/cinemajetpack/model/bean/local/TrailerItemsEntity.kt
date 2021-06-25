package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trailerEntity",
    primaryKeys = ["trailerId", "detailId"],
    foreignKeys = [ForeignKey(entity = DetailEntity::class,
        parentColumns = ["detailId"],
        childColumns = ["detailId"])],
    indices = [Index(value = ["trailerId"]),
        Index(value = ["detailId"])])
data class TrailerItemsEntity(
    @field:SerializedName("id")
    @ColumnInfo(name = "trailerId")
    val trailerId: String,

    @ColumnInfo(name = "detailId")
    var detailId: Int,

    @field:SerializedName("site")
    @ColumnInfo(defaultValue = "", name = "site")
    val site: String,

    @field:SerializedName("size")
    @ColumnInfo(defaultValue = "", name = "size")
    val size: Int,

    @field:SerializedName("iso_3166_1")
    @ColumnInfo(defaultValue = "", name = "iso_3166_1")
    val iso31661: String,

    @ColumnInfo(defaultValue = "", name = "name")
    val name: String,

    @ColumnInfo(defaultValue = "", name = "type")
    val type: String,

    @field:SerializedName("iso_639_1")
    @ColumnInfo(defaultValue = "", name = "iso_639_1")
    val iso6391: String,

    @field:SerializedName("key")
    @ColumnInfo(defaultValue = "", name = "key")
    val key: String
)
