package com.mayarafernandes.movieplayer.login

import android.content.Intent
import com.mayarafernandes.movieplayer.DisplayMessage
import com.mayarafernandes.movieplayer.login.view.LoginActivity

class LoginNavigator(private val activity: LoginActivity) {

    fun goToMainScreen(loginMessage: String) {
        val mainScreenIntent = Intent(activity, DisplayMessage::class.java). apply {
            putExtra(LoginController.EXTRA_MESSAGE, loginMessage)
        }

        activity.startActivity(mainScreenIntent)
    }
}