package com.mayarafernandes.movieplayer.login

class SharedPrefAccess(): EmailDb {
    override fun getEmail(): String {
        val sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        val savedEmail = sharedPref.getString("savedEmail", null)
    }
}