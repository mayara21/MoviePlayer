package com.mayarafernandes.movieplayer.login

interface LoginView {
    //fun startActivity(intent: Intent)
    fun finish()
    fun showLoginErrorMessageText(loginErrorMessage: String)
    fun setEmailEditText(savedEmail: String?)

}