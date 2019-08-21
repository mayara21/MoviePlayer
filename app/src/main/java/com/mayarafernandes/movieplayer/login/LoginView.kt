package com.mayarafernandes.movieplayer.login

import android.content.Intent

interface LoginView {
    fun startActivity(intent: Intent)

    fun finish()

    fun getSharedPreferences(name: String, mode: Int)

    fun showloginErrorMessageText(loginErrorMessage: String)
    fun setEmailEditText(savedEmail: String)

}