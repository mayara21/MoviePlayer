package com.mayarafernandes.movieplayer.movieList

import android.view.View

class OnClickMovieListener(private val position: Int): View.OnClickListener {

    override fun onClick(view: View?) {
        println("position is $position")
    }
}