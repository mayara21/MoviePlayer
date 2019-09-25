package com.mayarafernandes.movieplayer.login

import com.mayarafernandes.movieplayer.login.storage.EmailDb
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class SavedEmailImplTest {
    private val db = mock<EmailDb>()
    private val savedEmail = SavedEmailImpl(db)
    private val email = "admin@mail.com"

    @Test
    fun `test when call showSavedEmail expect show email`() {
        savedEmail.showSavedEmail()
        verify(db).getEmail()
    }

    @Test
    fun `test when call save expect save email`() {
        savedEmail.save(email)
        verify(db).saveEmail(email)
    }

    @Test
    fun `test when call showSavedEmail expect saved email`() {
        whenever(db.getEmail()).thenReturn(email)
        val result = savedEmail.showSavedEmail()

        assertEquals(email, result)
    }
}