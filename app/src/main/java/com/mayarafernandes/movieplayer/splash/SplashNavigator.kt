package com.mayarafernandes.movieplayer.splash

import android.content.Intent
import com.mayarafernandes.movieplayer.login.view.LoginActivity

class SplashNavigator (private val activity: SplashActivity){

    fun goToLogin() {
        val loginIntent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(loginIntent)
    }
}