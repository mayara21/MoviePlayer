package com.mayarafernandes.movieplayer.login

import com.mayarafernandes.movieplayer.login.storage.EmailDb

class SavedEmail(private val db: EmailDb) {

    fun showSavedEmail(): String? {
        return db.getEmail()
    }

    fun save(email: String) {
        return db.saveEmail(email)
    }
}