package com.mayarafernandes.movieplayer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*

const val EXTRA_MESSAGE = "com.mayarafernandes.movieplayer.MESSAGE"
const val LOGIN_ERROR_MESSAGE = "E-mail ou senha incorretos"
const val MASTER_EMAIL = "admin@mail.com"
const val MASTER_PASSWORD = "admin123"


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        val savedEmail = sharedPref.getString("savedEmail", null)

        emailEditText.setText(savedEmail, TextView.BufferType.EDITABLE)

        val emailBox = emailEditText
        val passwordBox = passwordEditText

        emailBox.setOnFocusChangeListener { view, b -> removeErrorMessage(b) }
        passwordBox.setOnFocusChangeListener { view, b -> removeErrorMessage(b) }



    }

    private fun removeErrorMessage(b: Boolean) {
        if(b) loginErrorMessageText.text = ""
    }


    fun onLogin(view: View) {

        //val email = findViewById<EditText>(R.id.emailEditText).text.toString()
        //val password = findViewById<EditText>(R.id.passwordEditText).text.toString()


        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if(email == MASTER_EMAIL && password == MASTER_PASSWORD) {
            val sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString("savedEmail", email)
                apply()
                //commit()
            }

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
