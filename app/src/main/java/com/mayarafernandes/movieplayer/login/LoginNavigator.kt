package com.mayarafernandes.movieplayer.login

import android.content.Intent
import com.mayarafernandes.movieplayer.BottomNavigationActivity
import com.mayarafernandes.movieplayer.login.view.LoginActivity

class LoginNavigator(private val activity: LoginActivity) {

    fun goToMainScreen() {
        val mainScreenIntent = Intent(activity, BottomNavigationActivity::class.java)

        activity.startActivity(mainScreenIntent)
    }
}