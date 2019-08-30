package com.mayarafernandes.movieplayer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mayarafernandes.movieplayer.movieList.view.MovieListActivity

class DisplayMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

    }

    fun onClickForget(view: View) {
        val sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            remove("savedEmail")
            apply()
        }
    }

    fun goToList(view: View) {
        val listScreenIntent = Intent(this, MovieListActivity::class.java)
        startActivity(listScreenIntent)
    }
}
