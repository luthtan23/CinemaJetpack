package com.luthtan.cinemajetpack.model.bean.response.login

import com.google.gson.annotations.SerializedName

data class ValidateResponse(

	@field:SerializedName("expires_at")
	val expiresAt: String,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("request_token")
	val requestToken: String,

	@field:SerializedName("status_message")
	val statusMessage: String,

	@field:SerializedName("status_code")
	val statusCode: Int
)
