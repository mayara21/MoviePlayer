package com.mayarafernandes.movieplayer.navigationBar.movies.repository

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.service.MovieCallbacks

interface MovieRepository {

    fun returnMovieList(movieCallbacks: MovieCallbacks)
    fun onMovieSelected(movieId: String): Movie
}