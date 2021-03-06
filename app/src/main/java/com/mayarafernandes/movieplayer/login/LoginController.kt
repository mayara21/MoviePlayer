package com.mayarafernandes.movieplayer.login

import com.mayarafernandes.movieplayer.login.view.LoginView

class LoginController (private val view: LoginView, private val savedEmail: SavedEmail, private val navigator: LoginNavigator, private val login: Login) {

    fun onViewCreated() {
        val email = savedEmail.showSavedEmail()

        view.setEmailEditText(email)
    }

    fun onLoginDo(email: String, password: String) {
        if(login.doLogin(email, password)) {
            navigator.goToMainScreen()
            view.finish()
        }

        else {
            view.showLoginErrorMessageText(LOGIN_ERROR_MESSAGE)
        }
    }

    companion object {
        const val LOGIN_ERROR_MESSAGE = "E-mail ou senha incorretos"
    }
}