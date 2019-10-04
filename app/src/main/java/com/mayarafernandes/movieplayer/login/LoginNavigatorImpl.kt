package com.mayarafernandes.movieplayer.login

import android.content.Intent
import com.mayarafernandes.movieplayer.navigationBar.BottomNavigationActivity
import com.mayarafernandes.movieplayer.login.view.LoginActivity

class LoginNavigatorImpl(private val activity: LoginActivity): LoginNavigator {

    override fun goToMainScreen() {
        val mainScreenIntent = Intent(activity, BottomNavigationActivity::class.java)

        activity.startActivity(mainScreenIntent)
    }
}