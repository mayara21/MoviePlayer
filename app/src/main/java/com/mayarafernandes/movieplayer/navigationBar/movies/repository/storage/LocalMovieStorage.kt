package com.mayarafernandes.movieplayer.navigationBar.movies.repository.storage

import com.mayarafernandes.movieplayer.navigationBar.movies.repository.Movie

interface LocalMovieStorage {

    fun saveMovieList(movieList: List<Movie>)
    fun returnMovieList(): List<Movie>
    fun getMovieById(movieId: String): Movie
}