package com.mayarafernandes.movieplayer.login

interface Login {
    fun doLogin(email: String, password: String): Boolean
}