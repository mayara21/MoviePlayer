package com.mayarafernandes.movieplayer.movieList.repository.service

import com.mayarafernandes.movieplayer.movieList.repository.Movie

interface MovieCallbacks {
    fun onSuccess(movieList: List<Movie>)
    fun onError()
}