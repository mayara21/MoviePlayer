package com.mayarafernandes.movieplayer.login.storage

interface EmailDb {
    fun getEmail(): String?
    fun saveEmail(email: String)
}
