package com.mayarafernandes.movieplayer.movieList.repository.service

interface MovieService {
    fun returnMovieList(movieServiceCallback: MovieCallbacks)
}