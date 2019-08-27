package com.mayarafernandes.movieplayer.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.mayarafernandes.movieplayer.R
import com.mayarafernandes.movieplayer.login.Login
import com.mayarafernandes.movieplayer.login.LoginController
import com.mayarafernandes.movieplayer.login.LoginNavigator
import com.mayarafernandes.movieplayer.login.SavedEmail
import com.mayarafernandes.movieplayer.login.storage.SharedPrefAccess
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var controller: LoginController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPrefAccess = SharedPrefAccess(this)
        val savedEmail = SavedEmail(sharedPrefAccess)
        val navigator = LoginNavigator(this)
        val login = Login(savedEmail)

        controller = LoginController(this, savedEmail, navigator, login)

        controller.onViewCreated()

        val emailBox = emailEditText
        val passwordBox = passwordEditText

        emailBox.setOnFocusChangeListener { view, b -> removeErrorMessage(b) }
        passwordBox.setOnFocusChangeListener { view, b -> removeErrorMessage(b) }
    }

    override fun setEmailEditText(savedEmail: String?) {
        emailEditText.setText(savedEmail, TextView.BufferType.EDITABLE)
    }

    override fun showLoginErrorMessageText(loginErrorMessage: String) {
        loginErrorMessageText.text = loginErrorMessage
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