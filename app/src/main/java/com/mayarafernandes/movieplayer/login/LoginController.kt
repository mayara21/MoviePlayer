package com.mayarafernandes.movieplayer.login

class LoginController (private val view: LoginView, private val savedEmail: SavedEmail, private val navigator: LoginNavigator) {

    fun onViewCreated() {
        val email = savedEmail.showSavedEmail()

        view.setEmailEditText(email)
    }

    fun onLoginDo(email: String, password: String) {

        if(email == MASTER_EMAIL && password == MASTER_PASSWORD) {
            val loginMessage = "$email logado.\nSeja bem vindo!"

            savedEmail.save(email)
            navigator.goToMainScreen(loginMessage)
            view.finish()
        }

        else {
            view.showLoginErrorMessageText(LOGIN_ERROR_MESSAGE)
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "com.mayarafernandes.movieplayer.MESSAGE"
        const val LOGIN_ERROR_MESSAGE = "E-mail ou senha incorretos"
        const val MASTER_EMAIL = "admin@mail.com"
        const val MASTER_PASSWORD = "admin123"
    }
}