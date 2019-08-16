package com.mayarafernandes.movieplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_MESSAGE = "com.mayarafernandes.movieplayer.MESSAGE"
const val LOGIN_ERROR_MESSAGE = "E-mail ou senha incorretos"
const val MASTER_EMAIL = "admin@mail.com"
const val MASTER_PASSWORD = "admin123"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailBox = emailEditText
        val passwordBox = passwordEditText

        emailBox.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) { loginErrorMessageText.text = "" }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        passwordBox.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) { loginErrorMessageText.text = "" }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })


    }


    fun onLogin(view: View) {

        //val email = findViewById<EditText>(R.id.emailEditText).text.toString()
        //val password = findViewById<EditText>(R.id.passwordEditText).text.toString()

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if(email == MASTER_EMAIL && password == MASTER_PASSWORD) {
            val message = "$email logado.\nSeja bem vindo!"

            val intent = Intent(this, DisplayMessage::class.java). apply {
                putExtra(EXTRA_MESSAGE, message)
            }

            startActivity(intent)
        }

        else {
            loginErrorMessageText.text = LOGIN_ERROR_MESSAGE
        }


    }

}
