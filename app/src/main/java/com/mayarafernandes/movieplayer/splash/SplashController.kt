package com.mayarafernandes.movieplayer.splash

import android.os.Handler

class SplashController (private val navigator: SplashNavigator, private val view: SplashView, private val handler: Handler){

    fun onViewCreated() {
        handler.postDelayed( {
            navigator.goToLogin()

            view.finish()
        }, SPLASH_TIMER)
    }

    companion object { const val SPLASH_TIMER = 2000L }
}