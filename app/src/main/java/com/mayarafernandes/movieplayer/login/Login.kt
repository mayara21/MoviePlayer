package com.mayarafernandes.movieplayer.login

class Login (private val savedEmail: SavedEmail){

    fun doLogin(email: String, password: String): Boolean {
        return if(email == MASTER_EMAIL && password == MASTER_PASSWORD) {
            savedEmail.save(email)
            true
        } else false
    }

    companion object {
        private const val MASTER_EMAIL = "admin@mail.com"
        private const val MASTER_PASSWORD = "admin123"
    }
}