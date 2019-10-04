package com.mayarafernandes.movieplayer.navigationBar.movies.repository.service

interface MovieService {
    fun returnMovieList(movieServiceCallback: MovieCallbacks)
}