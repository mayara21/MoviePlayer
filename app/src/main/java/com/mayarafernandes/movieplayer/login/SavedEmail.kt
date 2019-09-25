package com.mayarafernandes.movieplayer.login

interface SavedEmail {

    fun showSavedEmail(): String?

    fun save(email: String)
}