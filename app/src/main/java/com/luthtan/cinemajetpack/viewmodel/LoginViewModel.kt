package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.repository.login.LoginRepository
import com.luthtan.cinemajetpack.vo.Resource
import org.koin.core.KoinComponent

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(), KoinComponent {

    private val _tokenResponse = MutableLiveData<Resource<TokenResponse>>()
    val tokenResponse: LiveData<Resource<TokenResponse>> get() = _tokenResponse

    private val _validateResponse = MutableLiveData<Resource<ValidateResponse>>()
    val validateResponse: LiveData<Resource<ValidateResponse>> get() = _validateResponse

    fun getTokenLogin() {
        loginRepository.getTokenLogin(_tokenResponse)
    }

    fun getValidateLogin(validateRequest: ValidateRequest) {
        loginRepository.getValidateLogin(_validateResponse, validateRequest)
    }


}