package com.mayarafernandes.movieplayer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mayarafernandes.movieplayer.login.LoginController.Companion.EXTRA_MESSAGE
import kotlinx.android.synthetic.main.activity_display_message.*

class DisplayMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message = intent.getStringExtra(EXTRA_MESSAGE)


        welcomeMessageText.text = message
    }

    fun onClickForget(view: View) {
        val sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            remove("savedEmail")
            apply()
            //commit()
        }

    }
}
