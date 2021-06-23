package com.luthtan.cinemajetpack.model.bean.response.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("page")
    var page: Int,

    @field:SerializedName("total_pages")
    var totalPages: Int,

    @field:SerializedName("results")
    var results: List<MovieResultsItem>,

    @field:SerializedName("total_results")
    var totalResults: Int
)
