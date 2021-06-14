package com.luthtan.cinemajetpack.model.bean.response.login

import com.google.gson.annotations.SerializedName

data class TokenResponse(

    @field:SerializedName("expires_at")
    val expiresAt: String? = null,

    @field:SerializedName("success")
    val success: Boolean = false,

    @field:SerializedName("request_token")
    val requestToken: String? = null
)
