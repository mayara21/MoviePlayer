package com.mayarafernandes.movieplayer.login

class SavedEmail(private val db: EmailDb) {

    fun showSavedEmail(): String {
        return db.getEmail()
    }
}