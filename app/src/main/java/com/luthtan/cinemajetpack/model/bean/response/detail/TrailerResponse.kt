package com.luthtan.cinemajetpack.model.bean.response.detail

import com.google.gson.annotations.SerializedName

data class TrailerResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("results")
    val results: List<TrailerItems>
)
