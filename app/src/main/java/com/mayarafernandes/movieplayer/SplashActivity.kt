package com.mayarafernandes.movieplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

const val SPLASH_TIMER = 2000L

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed( Runnable(){
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)

            finish()
        }, SPLASH_TIMER)
    }
}
