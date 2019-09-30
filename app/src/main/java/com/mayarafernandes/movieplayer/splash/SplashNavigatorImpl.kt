package com.mayarafernandes.movieplayer.splash

import android.content.Intent
import com.mayarafernandes.movieplayer.login.view.LoginActivity

class SplashNavigatorImpl (private val activity: SplashActivity): SplashNavigator {

    override fun goToLogin() {
        val loginIntent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(loginIntent)
    }
}