package com.luthtan.cinemajetpack.model.bean.response.detail

import com.google.gson.annotations.SerializedName

data class CreditResponse(

    @field:SerializedName("cast")
    val cast: List<CastItem>,

    @field:SerializedName("id")
    val id: Int,

    )

