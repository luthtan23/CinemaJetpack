package com.luthtan.cinemajetpack.repository

import com.luthtan.cinemajetpack.model.local.preference.PreferenceConstants
import com.luthtan.cinemajetpack.model.local.preference.PreferenceHelper

class PreferencesRepository(private val preferences: PreferenceHelper) {

    fun setIsDarkMode(status: String) {
        preferences.put(PreferenceConstants.IS_DARK_MODE, status)
    }

    fun getIsDarkMode(): String {
        return preferences.get(PreferenceConstants.IS_DARK_MODE)
    }

    fun setValidateRequest(token: String, username: String, password: String) {
        preferences.put(PreferenceConstants.TOKEN_REQUEST, token)
        preferences.put(PreferenceConstants.USERNAME_REQUEST, username)
        preferences.put(PreferenceConstants.PASSWORD_REQUEST, password)
    }

    fun getTokenRequest(): String {
        return preferences.get(PreferenceConstants.TOKEN_REQUEST)
    }

    fun getUsernameRequest(): String {
        return preferences.get(PreferenceConstants.USERNAME_REQUEST)
    }

    fun getPasswordRequest(): String {
        return preferences.get(PreferenceConstants.PASSWORD_REQUEST)
    }

}