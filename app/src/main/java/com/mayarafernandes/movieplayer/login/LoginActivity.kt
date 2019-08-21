package com.mayarafernandes.movieplayer.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.mayarafernandes.movieplayer.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginView {
    override fun setEmailEditText(savedEmail: String) {
        emailEditText.setText(savedEmail, TextView.BufferType.EDITABLE)
    }

    override fun showloginErrorMessageText(loginErrorMessage: String) {
        loginErrorMessageText.text = loginErrorMessage
    }

    private val controller = LoginController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        controller.onViewCreated()

        val emailBox = emailEditText
        val passwordBox = passwordEditText

        emailBox.setOnFocusChangeListener { view, b -> removeErrorMessage(b) }
        passwordBox.setOnFocusChangeListener { view, b -> removeErrorMessage(b) }
    }

    private fun removeErrorMessage(b: Boolean) {
        if(b) loginErrorMessageText.text = ""
    }

    fun onLogin(view: View) {

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        controller.onLoginDo(email, password)
    }


}
