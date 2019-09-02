package com.mayarafernandes.movieplayer.login

import android.content.Intent
import com.mayarafernandes.movieplayer.login.view.LoginActivity
import com.mayarafernandes.movieplayer.movieList.view.MovieListActivity

class LoginNavigator(private val activity: LoginActivity) {

    fun goToMainScreen() {
        val mainScreenIntent = Intent(activity, MovieListActivity::class.java)

        activity.startActivity(mainScreenIntent)
    }
}