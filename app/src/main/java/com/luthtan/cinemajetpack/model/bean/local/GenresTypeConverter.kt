package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class GenresTypeConverter {

    @TypeConverter
    fun loadToJson(value: List<GenresItemEntity>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): List<GenresItemEntity> = Gson().fromJson(value, Array<GenresItemEntity>::class.java).toList()
}