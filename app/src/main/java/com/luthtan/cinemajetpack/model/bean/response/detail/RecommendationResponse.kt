package com.luthtan.cinemajetpack.model.bean.response.detail

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<RecommendationItems>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)
