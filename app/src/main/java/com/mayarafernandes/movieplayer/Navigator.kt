package com.mayarafernandes.movieplayer

import android.app.Activity
import android.content.Intent

class Navigator(private val activity: Activity) {
    fun goToSelectedMovie(movie: PlayerMediaModel) {
        val movieIntent = Intent(activity, MovieActivity::class.java)
        movieIntent.putExtra("MOVIE_ID", movie.id)
        movieIntent.putExtra("MOVIE_TITLE", movie.title)
        movieIntent.putExtra("MOVIE_DESCRIPTION", movie.description)
        movieIntent.putExtra("VIDEO_URL", movie.videoUrl)
        movieIntent.putExtra("VIDEO_ID", movie.videoId)
        movieIntent.putExtra("VIDEO_DURATION", movie.duration)

        activity.startActivity(movieIntent)
    }
}