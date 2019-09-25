package com.mayarafernandes.movieplayer.login

import com.mayarafernandes.movieplayer.login.storage.EmailDb

class SavedEmailImpl(private val db: EmailDb): SavedEmail {

    override fun showSavedEmail(): String? {
        return db.getEmail()
    }

    override fun save(email: String) {
        return db.saveEmail(email)
    }
}