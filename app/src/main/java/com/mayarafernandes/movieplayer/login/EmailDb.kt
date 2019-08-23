package com.mayarafernandes.movieplayer.login

interface EmailDb {
    fun getEmail(): String?
    fun saveEmail(email: String)
}
