package com.mayarafernandes.movieplayer.login

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Test

class LoginTest {
    private val savedEmail = mock<SavedEmail>()
    private val login = LoginImpl(savedEmail)

    @Test
    fun `test when call doLogin expect true`() {
        val result = login.doLogin("admin@mail.com", "admin123")
        assertTrue(result)
    }

    @Test
    fun `test when call doLogin expect false`() {
        val result = login.doLogin("admin@mail.br", "admin123")
        assertFalse(result)
    }

    @Test
    fun `test when call doLogin expect save email`() {
        login.doLogin("admin@mail.com", "admin123")
        verify(savedEmail).save("admin@mail.com")
    }

    @Test
    fun `test when call doLogin expect not save email`() {
        login.doLogin("admin@mail.cm", "admin123")
        verifyNoMoreInteractions(savedEmail)
    }
}