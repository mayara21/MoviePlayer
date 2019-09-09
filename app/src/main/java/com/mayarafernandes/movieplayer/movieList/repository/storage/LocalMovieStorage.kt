package com.mayarafernandes.movieplayer.movieList.repository.storage

import com.mayarafernandes.movieplayer.movieList.repository.Movie

interface LocalMovieStorage {

    fun saveMovieList(movieList: List<Movie>)
    fun returnMovieList(): List<Movie>
    fun getMovieById(movieId: String): Movie
}