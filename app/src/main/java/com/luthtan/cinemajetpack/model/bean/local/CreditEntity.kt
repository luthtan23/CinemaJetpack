package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "creditEntity")
data class CreditEntity(
    @field:SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    /*@field:SerializedName("cast")
    @ColumnInfo(name = "cast")
    @Embedded
    val cast: List<CastItemEntity>,*/

)
