package com.mayarafernandes.movieplayer.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mayarafernandes.movieplayer.R


class SplashActivity : AppCompatActivity(), SplashView {

    private val navigator = SplashNavigatorImpl(this)
    private val controller: SplashController = SplashController(navigator, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        controller.onViewCreated()
    }
}
