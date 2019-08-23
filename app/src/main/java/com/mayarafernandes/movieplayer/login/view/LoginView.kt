package com.mayarafernandes.movieplayer.login.view

interface LoginView {
    fun finish()
    fun showLoginErrorMessageText(loginErrorMessage: String)
    fun setEmailEditText(savedEmail: String?)

}