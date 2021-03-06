package com.mayarafernandes.movieplayer

import android.app.Application
import com.mayarafernandes.movieplayer.movieList.favorites.repository.MovieRoomDatabase

class MoviePlayerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MovieRoomDatabase.init(this)
    }
}