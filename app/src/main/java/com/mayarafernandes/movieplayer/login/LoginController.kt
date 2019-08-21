package com.mayarafernandes.movieplayer.login

import android.content.Context
import android.content.Intent
import com.mayarafernandes.movieplayer.DisplayMessage
import com.mayarafernandes.movieplayer.R


class LoginController (private val view: LoginView, private val savedEmail: SavedEmail) {

    fun onViewCreated() {



        val email = savedEmail.showSavedEmail()

        view.setEmailEditText(email)
    }

    fun onLoginDo(email: String, password: String) {

        if(email == MASTER_EMAIL && password == MASTER_PASSWORD) {
            val sharedPref = view.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString("savedEmail", email)
                apply()
            }

            val message = "$email logado.\nSeja bem vindo!"

            val intent = Intent(activity, DisplayMessage::class.java). apply {
                putExtra(EXTRA_MESSAGE, message)
            }

            view.startActivity(intent)

            view.finish()
        }

        else {
            view.showloginErrorMessageText(LOGIN_ERROR_MESSAGE)
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "com.mayarafernandes.movieplayer.MESSAGE"
        const val LOGIN_ERROR_MESSAGE = "E-mail ou senha incorretos"
        const val MASTER_EMAIL = "admin@mail.com"
        const val MASTER_PASSWORD = "admin123"
    }
}