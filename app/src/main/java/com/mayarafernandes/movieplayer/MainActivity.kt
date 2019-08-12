package com.mayarafernandes.movieplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

const val EXTRA_MESSAGE = "com.mayarafernandes.movieplayer.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onLogin(view: View) {

        val emailText = findViewById<EditText>(R.id.editText3)
        val email = emailText.text.toString()
        val message = "$email logado!"
        
        val intent = Intent(this, DisplayMessage::class.java). apply {
            putExtra(EXTRA_MESSAGE, message)
        }

        startActivity(intent)

    }

}
