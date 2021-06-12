package com.luthtan.cinemajetpack.ui.user

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.UserFragmentLayoutBinding
import com.luthtan.cinemajetpack.repository.PreferencesRepository
import com.luthtan.cinemajetpack.ui.MainActivity
import com.luthtan.cinemajetpack.util.Constant
import org.koin.android.ext.android.inject

class UserFragment : Fragment(), View.OnClickListener {

    private val preferences: PreferencesRepository by inject()

    private var _binding: UserFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemUserSetting.setOnClickListener(this)
        binding.itemUserLogout.setOnClickListener(this)

        binding.tvUserUsername.text = preferences.getUsernameRequest()

        (activity as MainActivity).showBottomNav()
    }

    private fun showAlertLogout() {
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setMessage(getString(R.string.confirm_alert_logout))
            .setNegativeButton(getString(R.string.cancel)) { dialog, which -> dialog.cancel() }
            .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                preferences.setValidateRequest("", "", "")
                restartApp()
            }

        val alert = alertDialog.create()
        alert.show()
    }

    private fun restartApp() {
        activity?.finish()
        startActivity(activity?.intent)
        activity?.overridePendingTransition(0, 0)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.item_user_setting -> requireView().findNavController().navigate(UserFragmentDirections.actionUserFragmentToSettingFragment(Constant.USER_SETTING))
            R.id.item_user_logout -> showAlertLogout()
        }
    }

}