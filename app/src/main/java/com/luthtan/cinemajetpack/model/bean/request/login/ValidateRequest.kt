package com.luthtan.cinemajetpack.model.bean.request.login

import com.google.gson.annotations.SerializedName

data class ValidateRequest(

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("request_token")
    val requestToken: String,

    @field:SerializedName("username")
    val username: String
)
