package com.mayarafernandes.movieplayer.login

import com.mayarafernandes.movieplayer.login.LoginController.Companion.LOGIN_ERROR_MESSAGE
import com.mayarafernandes.movieplayer.login.view.LoginView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class LoginControllerTest {

    private val view = mock<LoginView>()
    private val savedEmail = mock<SavedEmail>()
    private val navigator = mock<LoginNavigator>()
    private val login = mock<Login>()
    private val controller = LoginController(view, savedEmail, navigator, login)

    private val email = "test@mail.com"
    private val password = "test"

    @Test
    fun `test when call onViewCreated expect view to show savedEmail`() {
        whenever(savedEmail.showSavedEmail()).thenReturn(email)
        controller.onViewCreated()
        verify(view).setEmailEditText(email)
    }

    @Test
    fun `test when call onLoginDo expect call doLogin`() {
        controller.onLoginDo(email, password)
        verify(login).doLogin(email, password)
    }

    @Test
    fun `test when call onLoginDo expect go to main screen when login successful`() {
        whenever(login.doLogin(email, password)).thenReturn(true)
        controller.onLoginDo(email, password)

        verify(navigator).goToMainScreen()
    }

    @Test
    fun `test when call onLoginDo expect finish view when login successful`() {
        whenever(login.doLogin(email, password)).thenReturn(true)
        controller.onLoginDo(email, password)

        verify(view).finish()
    }

    @Test
    fun `test when call onLoginDo expect error message when login fail`() {
        whenever(login.doLogin(email, password)).thenReturn(true)
        controller.onLoginDo(email, "fail")

        verify(view).showLoginErrorMessageText("E-mail ou senha incorretos")
    }
}