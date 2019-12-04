package com.mayarafernandes.movieplayer

import android.app.Activity
import android.content.Intent

class Navigator(private val activity: Activity) {
    fun goToSelectedMovie() {
        val movieIntent = Intent(activity, MovieActivity::class.java)

        activity.startActivity(movieIntent)
    }
}