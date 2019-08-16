package com.mayarafernandes.movieplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_display_message.*

class DisplayMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        //val textView4 = findViewById<TextView>(R.id.textView4).apply {
        //    text = message
        //}
        welcomeMessageText.text = message
    }
}
