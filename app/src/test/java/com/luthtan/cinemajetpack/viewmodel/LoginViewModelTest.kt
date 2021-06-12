package com.luthtan.cinemajetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.repository.LoginRepository
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var loginViewModel: LoginViewModel

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Mock
    private lateinit var observer: Observer<ValidateResponse>

    @Mock
    private lateinit var tokenObserver: Observer<TokenResponse>

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun getTokenResponse() {
        val tokenResponse = loginViewModel.getTokenLogin()
        assertNotNull(tokenResponse)
        loginViewModel.tokenResponse.observeForever(tokenObserver)
    }

    @Test
    fun getValidateResponse() {
        loginViewModel.validateResponse.observeForever(observer)
    }

    @Test
    fun getErrorResponse() {
    }

    @Test
    fun getTokenLogin() {
    }

    @Test
    fun getValidateLogin() {
    }
}