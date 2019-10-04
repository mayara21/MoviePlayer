package com.mayarafernandes.movieplayer.navigationBar.movies.repository.service

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie

interface MovieCallbacks {
    fun onSuccess(movieList: List<Movie>)
    fun onError()
}