package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GenresItemEntity(

    @PrimaryKey
    @field:SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,

    @field:SerializedName("name")
    @ColumnInfo(name ="name")
    val name: String


)