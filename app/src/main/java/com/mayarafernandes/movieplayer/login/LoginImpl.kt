package com.mayarafernandes.movieplayer.login

class LoginImpl (private val savedEmail: SavedEmail): Login {

    override fun doLogin(email: String, password: String): Boolean {
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