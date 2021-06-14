package com.luthtan.cinemajetpack.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.luthtan.cinemajetpack.databinding.SplashScreenFragmentLayoutBinding
import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.repository.PreferencesRepository
import com.luthtan.cinemajetpack.viewmodel.LoginViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenFragment : Fragment() {

    private var _binding: SplashScreenFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private val handler = Handler(Looper.getMainLooper())

    private val preferences: PreferencesRepository by inject()
    private val loginViewModel: LoginViewModel by viewModel()

    companion object {
        private const val HOME_FRAGMENT_LATENCY_IN_MILLIS: Long = 3000
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashScreenFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val validateRequest = ValidateRequest(
            preferences.getPasswordRequest(),
            preferences.getTokenRequest(),
            preferences.getUsernameRequest()
        )

        loginViewModel.getValidateLogin(validateRequest)

        loginViewModel.validateResponse.observe(viewLifecycleOwner, { validateResponse ->
            if (validateResponse == null) {
                splashLogin()
                return@observe
            }
            splashEnd()
        })

        loginViewModel.errorResponse.observe(viewLifecycleOwner, { errorResponse ->
            if (errorResponse != null) {
                splashLogin()
            }
        })
    }

    private fun splashEnd() {
        handler.postDelayed({ homeFragment() }, HOME_FRAGMENT_LATENCY_IN_MILLIS)
    }

    private fun homeFragment() {
        try {
            view?.findNavController()
                ?.navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToBottomNavFragment())
        } catch (e: Exception) {
            e.printStackTrace()
            splashLogin()
        }
    }

    private fun splashLogin() {
        handler.postDelayed({ loginFragment() }, HOME_FRAGMENT_LATENCY_IN_MILLIS)
    }

    private fun loginFragment() {
        view?.findNavController()
            ?.navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment())
    }

}