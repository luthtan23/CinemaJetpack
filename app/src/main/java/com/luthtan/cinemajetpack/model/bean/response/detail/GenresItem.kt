package com.luthtan.cinemajetpack.model.bean.response.detail

import com.google.gson.annotations.SerializedName

data class GenresItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
