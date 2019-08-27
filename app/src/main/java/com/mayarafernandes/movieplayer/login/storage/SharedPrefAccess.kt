package com.mayarafernandes.movieplayer.login.storage

import android.content.Context
import com.mayarafernandes.movieplayer.R

class SharedPrefAccess(context: Context): EmailDb {

    private val sharedPrefName = context.getString(R.string.preference_file_key)
    private val sharedPref = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)

    override fun getEmail(): String? {
        return sharedPref.getString("savedEmail", null)
    }

    override fun saveEmail(email: String) {
        with (sharedPref.edit()) {
            putString("savedEmail", email)
            apply()
        }
    }
}