package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.repository.login.LoginRepository
import com.luthtan.cinemajetpack.vo.Resource
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var loginViewModel: LoginViewModel

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Mock
    private lateinit var observer: Observer<Resource<ValidateResponse>>

    @Mock
    private lateinit var tokenObserver: Observer<Resource<TokenResponse>>

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun getTokenResponse() {
        Thread {
            val mutableTokenResponse = MutableLiveData<Resource<TokenResponse>>()
            val tokenResponse = loginViewModel.getTokenLogin()
            verify(loginRepository).getTokenLogin(mutableTokenResponse)
            assertNotNull(tokenResponse)
            loginViewModel.tokenResponse.observeForever(tokenObserver)
        }
    }

    @Test
    fun getValidateResponse() {
        Thread {
            val mutableValidateResponse = MutableLiveData<Resource<ValidateResponse>>()
            val tokenResponse = loginViewModel.tokenResponse.value!!.data!!.requestToken!!
            val validateRequest = ValidateRequest("admin001", tokenResponse, "mangbrad23")
            val validateResponse = loginViewModel.getValidateLogin(validateRequest)
            verify(loginRepository).getValidateLogin(mutableValidateResponse, validateRequest)
            assertNotNull(validateResponse)
            loginViewModel.validateResponse.observeForever(observer)
        }

    }

}