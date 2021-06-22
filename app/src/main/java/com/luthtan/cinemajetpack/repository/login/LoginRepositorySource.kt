package com.luthtan.cinemajetpack.repository.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.vo.Resource

interface LoginRepositorySource {

    fun getTokenLogin(tokenResponse: MutableLiveData<Resource<TokenResponse>>): LiveData<Resource<TokenResponse>>

    fun getValidateLogin(
        validateResponse: MutableLiveData<Resource<ValidateResponse>>,
        validateRequest: ValidateRequest
    ): LiveData<Resource<ValidateResponse>>
}