package com.mayarafernandes.movieplayer.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mayarafernandes.movieplayer.R


class SplashActivity : AppCompatActivity(), SplashView {

    private val navigator = SplashNavigatorImpl(this)
    private val controller: SplashController = SplashController(navigator, this, Handler())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        controller.onViewCreated()
    }
}
