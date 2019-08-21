package com.mayarafernandes.movieplayer.login

import org.junit.Test

import org.junit.Assert.*

class SavedEmailTest {

    private val savedEmail = SavedEmail()

    @Test
    fun showSavedEmail() {
        val email = savedEmail.showSavedEmail()
        assertEquals("admin@mail.com", email)
    }
}