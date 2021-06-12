package com.luthtan.cinemajetpack.ui.setting

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.SettingFragmentLayoutBinding
import com.luthtan.cinemajetpack.repository.PreferencesRepository
import com.luthtan.cinemajetpack.ui.MainActivity
import com.luthtan.cinemajetpack.util.Constant
import org.koin.android.ext.android.inject

class SettingFragment : Fragment() {

    private val preferences: PreferencesRepository by inject()

    private var _binding: SettingFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingFragmentLayoutBinding.inflate(inflater, container, false)
        val actionBar = (activity as MainActivity)
        actionBar.setSupportActionBar(binding.settingToolbar)
        actionBar.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backPressedFragment()

        val extraSetting = SettingFragmentArgs.fromBundle(arguments as Bundle).identifyFragment

        if (extraSetting == Constant.USER_SETTING) {
            (activity as MainActivity).hideBottomNav()
            binding.settingToolbar.visibility = View.GONE
        }

        setStatusTheme(preferences.getIsDarkMode())

        binding.switchSetting.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                setStatusTheme(Constant.DARK_THEME)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                setStatusTheme(Constant.LIGHT_THEME)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStatusTheme(status: String) {
        when(status) {
            Constant.DARK_THEME -> {
                preferences.setIsDarkMode(Constant.DARK_THEME)
                with(binding) {
                    switchSetting.isChecked = true
                    tvSwitchSetting.text = getString(R.string.dark_mode_on)
                    imageSetting.setImageDrawable(resources.getDrawable(R.drawable.ic_undraw_into_the_night_vumi))
                }
            }
            Constant.LIGHT_THEME -> {
                preferences.setIsDarkMode(Constant.LIGHT_THEME)
                with(binding) {
                    switchSetting.isChecked = false
                    tvSwitchSetting.text = getString(R.string.dark_mode_off)
                    imageSetting.setImageDrawable(resources.getDrawable(R.drawable.ic_undraw_sunny_day_bk3m))
                }
            }
        }
    }

    private fun backPressedFragment() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.findNavController()?.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> view?.findNavController()?.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}