package com.mayarafernandes.movieplayer.movieList.service

import com.mayarafernandes.movieplayer.movieList.Movie


interface MovieService {
    fun returnMovieList(): List<Movie>
}