package com.luthtan.cinemajetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.repository.LoginRepository
import org.koin.core.KoinComponent

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(), KoinComponent {

    private val _tokenResponse = MutableLiveData<TokenResponse>()
    val tokenResponse: LiveData<TokenResponse> get() = _tokenResponse

    private val _validateResponse = MutableLiveData<ValidateResponse>()
    val validateResponse: LiveData<ValidateResponse> get() = _validateResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    fun getTokenLogin() {
        loginRepository.getTokenLogin(_tokenResponse, _errorResponse)
    }

    fun getValidateLogin(validateRequest: ValidateRequest) {
        loginRepository.getValidateLogin(_validateResponse, _errorResponse, validateRequest)
    }


}