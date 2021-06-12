package com.luthtan.cinemajetpack.repository

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.BuildConfig
import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.model.remote.ApiService
import com.luthtan.cinemajetpack.model.remote.DataFetchCall
import org.koin.core.KoinComponent
import retrofit2.Response

class LoginRepository (private val apiService: ApiService) : KoinComponent {

    fun getTokenLogin(
        tokenResponse: MutableLiveData<TokenResponse>,
        errorResponse: MutableLiveData<String>
    ) {
        object : DataFetchCall<TokenResponse>(tokenResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<TokenResponse> {
                return apiService.getTokenLogin(BuildConfig.TOKEN)
            }
        }.execute()
    }

    fun getValidateLogin(
        validateResponse: MutableLiveData<ValidateResponse>,
        errorResponse: MutableLiveData<String>,
        validateRequest: ValidateRequest
    ) {
        object : DataFetchCall<ValidateResponse>(validateResponse, errorResponse) {
            override suspend fun createCallAsync(): Response<ValidateResponse> {
                return apiService.getValidateLogin(BuildConfig.TOKEN, validateRequest)
            }
        }.execute()
    }
}