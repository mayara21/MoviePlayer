package com.mayarafernandes.movieplayer.movieList.storage

import com.mayarafernandes.movieplayer.movieList.Movie

interface LocalMovieStorage {

    fun saveMovieList(movieList: List<Movie>)
    fun returnMovieList(): List<Movie>
    fun getMovieById(movieId: Int): Movie
}