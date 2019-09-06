package com.mayarafernandes.movieplayer.movieList.service

import com.mayarafernandes.movieplayer.movieList.Movie

interface MovieCallbacks {

    fun onSuccess(movieList: List<Movie>)
    fun onError()
}