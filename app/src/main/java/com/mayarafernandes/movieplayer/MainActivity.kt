package com.mayarafernandes.movieplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

const val EXTRA_MESSAGE = "com.mayarafernandes.movieplayer.MESSAGE"
const val MASTER_EMAIL = "admin@mail.com"
const val MASTER_PASSWORD = "admin123"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onLogin(view: View) {

        val email = findViewById<EditText>(R.id.emailEditText).text.toString()
        val password = findViewById<EditText>(R.id.passwordEditText).text.toString()

        if(email == MASTER_EMAIL && password == MASTER_PASSWORD) {
            val message = "$email logado.\nSeja bem vindo!"

            val intent = Intent(this, DisplayMessage::class.java). apply {
                putExtra(EXTRA_MESSAGE, message)
            }

            startActivity(intent)
        }


    }

}
