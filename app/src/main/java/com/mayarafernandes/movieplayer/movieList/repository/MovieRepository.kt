package com.mayarafernandes.movieplayer.movieList.repository

import com.mayarafernandes.movieplayer.movieList.repository.service.MovieCallbacks

interface MovieRepository {

    fun returnMovieList(movieCallbacks: MovieCallbacks)
    fun onMovieSelected(movieId: String): Movie
}