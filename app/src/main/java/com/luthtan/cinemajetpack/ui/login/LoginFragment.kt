package com.luthtan.cinemajetpack.ui.login

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.LoginFragmentLayoutBinding
import com.luthtan.cinemajetpack.model.bean.request.login.ValidateRequest
import com.luthtan.cinemajetpack.model.bean.response.login.TokenResponse
import com.luthtan.cinemajetpack.model.bean.response.login.ValidateResponse
import com.luthtan.cinemajetpack.repository.PreferencesRepository
import com.luthtan.cinemajetpack.util.Constant
import com.luthtan.cinemajetpack.util.Utils
import com.luthtan.cinemajetpack.viewmodel.LoginViewModel
import com.luthtan.cinemajetpack.vo.Resource
import com.luthtan.cinemajetpack.vo.Status
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), View.OnClickListener {

    private val loginViewModel: LoginViewModel by viewModel()
    private val preference: PreferencesRepository by inject()

    private var _binding: LoginFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var tokenResponse: TokenResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog.show()

        loginViewModel.getTokenLogin()

        binding.btnLoginLogin.setOnClickListener(this)
        binding.ibLoginSetting.setOnClickListener(this)

        tokenResponse = TokenResponse()

        loginViewModel.tokenResponse.observe(viewLifecycleOwner, tokenResponseResult)

        loginViewModel.validateResponse.observe(viewLifecycleOwner, validateResponse)


        Glide.with(requireContext())
            .load(R.drawable.logo_sub_2)
            .into(binding.ivLoginLogo)
    }

    private val tokenResponseResult: Observer<Resource<TokenResponse>> by lazy {
        Observer<Resource<TokenResponse>> { tokenResponse ->
            progressDialog.dismiss()
            if (tokenResponse != null) {
                progressDialog.dismiss()
                when (tokenResponse.status) {
                    Status.SUCCESS -> this.tokenResponse = tokenResponse.data!!
                    Status.ERROR -> Utils.snackBarErrorConnection(requireView(), requireContext())
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private val validateResponse: Observer<Resource<ValidateResponse>> by lazy {
        Observer<Resource<ValidateResponse>> { validateResponse ->
            if (validateResponse != null) {
                progressDialog.dismiss()
                when (validateResponse.status) {
                    Status.SUCCESS -> {
                        view?.findNavController()
                            ?.navigate(LoginFragmentDirections.actionLoginFragmentToBottomNavFragment())
                    }
                    Status.ERROR -> {
                        Utils.showAlertDialog(
                            requireContext(),
                            getString(R.string.title_alert_failed_login),
                            getString(R.string.message_alert_failed_login)
                        )
                        return@Observer
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context).apply {
            setMessage("Loading Please wait...")
        }
    }

    private fun getUsernameInput(tokenResponse: TokenResponse) {
        progressDialog.show()
        val username: String = binding.etUsername.text.toString().trim()
        val password: String = binding.etPassword.text.toString().trim()

        if (username.isEmpty()) {
            binding.etUsername.error = getString(R.string.field_error_edit_text)
            return
        }

        if (password.isEmpty()) {
            binding.etPassword.error = getString(R.string.field_error_edit_text)
            return
        }

        val validateRequest = ValidateRequest(password, tokenResponse.requestToken!!, username)
        preference.setValidateRequest(tokenResponse.requestToken, username, password)

        loginViewModel.getValidateLogin(validateRequest)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login_login -> {
                loginViewModel.getTokenLogin()
                if (tokenResponse.requestToken == null) {
                    Utils.snackBarErrorConnection(requireView(), requireContext())
                } else {
                    getUsernameInput(tokenResponse)
                }
            }
            R.id.ib_login_setting -> setSettingLoginPage()
        }
    }

    private fun setSettingLoginPage() {
        view?.findNavController()
            ?.navigate(LoginFragmentDirections.actionLoginFragmentToSettingFragment(Constant.LOGIN_SETTING))
    }


}